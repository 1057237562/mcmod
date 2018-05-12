package com.scirev.blocks.container.functional.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ElectricStorageEntity extends FunctionalEntity {

	public static String[] material = { "", "aluminum", "copper" };
	public Map<ElectricStorageEntity, Boolean> connected = new HashMap<ElectricStorageEntity, Boolean>();
	boolean refresh = false;

	public int power;
	public int maxpower;
	private int speed;
	private boolean d = true;

	public boolean debug = false;

	public ElectricStorageEntity(int conductance, boolean directional) {
		// TODO Auto-generated constructor stub
		speed = conductance;
		maxpower = speed;
		d = directional;
	}

	public ElectricStorageEntity(int conductance, int max, boolean directional) {
		// TODO Auto-generated constructor stub
		speed = conductance;
		maxpower = max;
		d = directional;
	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		super.updateEntity();
		if (!worldObj.isRemote) {
			detectOnLoad();
			if (power > 0 && connected.size() > 0) {
				ArrayList<ElectricStorageEntity> emptyconnection = new ArrayList<ElectricStorageEntity>();
				Set<ElectricStorageEntity> set = connected.keySet();
				for (ElectricStorageEntity component : set) {
					if (component.power < component.maxpower) {
						if (component.d) {
							emptyconnection.add(component);
						} else {
							if (component.power < power && !connected.get(component)) {
								emptyconnection.add(component);
								component.connected.put(this, true);
							}
						}
						connected.put(component, false);
					}
				}

				if (emptyconnection.size() > 0) {
					for (ElectricStorageEntity c : emptyconnection.toArray(new ElectricStorageEntity[0])) {
						c.addPower(speed / emptyconnection.size());
					}
					power -= speed;
				}
				debug = false;
			}
		}
	}

	private void detectOnLoad() {
		if (connected.size() == 0 && refresh == false) {
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord + 1, yCoord,
				        zCoord);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord - +1, yCoord,
				        zCoord);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord + 1,
				        zCoord);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord - 1,
				        zCoord);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord,
				        zCoord + 1);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord,
				        zCoord - 1);
				if (entity != null) {
					connected.put(entity, false);
				}
			} catch (ClassCastException e) {

			}
			refresh = true;
		}
	}

	public void addPower(int p) {
		// TODO Auto-generated method stub
		power += p;
	}

	public int getPower() {
		return power;
	}

	@Override
	public void NeighborBlockCreated(TileEntity which, int direction) {
		// TODO Auto-generated method stub
		try {
			ElectricStorageEntity cableEntity = (ElectricStorageEntity) which;
			connected.put(cableEntity, false);
		} catch (ClassCastException e) {
			// TODO: handle exception
		}

		super.NeighborBlockCreated(which, direction);
	}

	@Override
	public void NeighborBlockBroke(TileEntity which, int direction) {
		// TODO Auto-generated method stub
		try {
			ElectricStorageEntity cableEntity = (ElectricStorageEntity) which;
			connected.remove(cableEntity);
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		super.NeighborBlockBroke(which, direction);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		// TODO Auto-generated method stub
		super.readFromNBT(par1NBTTagCompound);
		this.d = par1NBTTagCompound.getBoolean("directional");
		this.power = par1NBTTagCompound.getShort("power");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		// TODO Auto-generated method stub
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("power", (short) power);
		par1NBTTagCompound.setBoolean("directional", d);
	}
}
