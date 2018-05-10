package com.scirev.blocks.container.functional.tileentity;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ElectricStorageEntity extends FunctionalEntity {

	public static String[] material = { "", "aluminum", "copper" };
	public ArrayList<ElectricStorageEntity> connected = new ArrayList<ElectricStorageEntity>();
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
				for (ElectricStorageEntity component : connected.toArray(new ElectricStorageEntity[0])) {
					if (component.power < component.maxpower) {
						if (component.d) {
							emptyconnection.add(component);
						} else {
							if (component.power < power) {
								if (debug) {
									System.out.println("added 1 component");
								}
								emptyconnection.add(component);
							}
						}

					}
				}

				if (emptyconnection.size() > 0) {
					for (ElectricStorageEntity c : emptyconnection.toArray(new ElectricStorageEntity[0])) {
						c.addPower(speed / emptyconnection.size());
						if (debug) {
							System.out.println(
							        "Power flow:" + (speed / emptyconnection.size()) + ",tE:"
							                + c.power);
						}
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
					connected.add(entity);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord - +1, yCoord,
				        zCoord);
				if (entity != null) {
					connected.add(entity);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord + 1,
				        zCoord);
				if (entity != null) {
					connected.add(entity);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord - 1,
				        zCoord);
				if (entity != null) {
					connected.add(entity);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord,
				        zCoord + 1);
				if (entity != null) {
					connected.add(entity);
				}
			} catch (ClassCastException e) {

			}
			try {
				ElectricStorageEntity entity = (ElectricStorageEntity) worldObj.getTileEntity(xCoord, yCoord,
				        zCoord - 1);
				if (entity != null) {
					connected.add(entity);
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
			connected.add(cableEntity);
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
