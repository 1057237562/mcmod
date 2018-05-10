package com.scirev.blocks.container.functional.tileentity;

import java.util.ArrayList;

import com.scirev.blocks.container.functional.Generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class GeneratorEntity extends FunctionalEntity implements IInventory {
	public int generatorBurnTime = 0;
	public int maxBurnTime = 0;
	private ItemStack stack[] = new ItemStack[1];

	public int power = 0;
	public int maxpower = 4000;
	private int GenerateSpeed = 10;

	private ArrayList<ElectricStorageEntity> connected = new ArrayList<ElectricStorageEntity>();
	boolean refresh;

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return false;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.stack[par1] != null) {
			ItemStack var3;
			if (this.stack[par1].stackSize <= par2) {
				var3 = this.stack[par1];
				this.stack[par1] = null;
				return var3;
			} else {
				var3 = this.stack[par1].splitStack(par2);
				if (this.stack[par1].stackSize == 0) {
					this.stack[par1] = null;
				}
				return var3;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return stack[var1];
	}

	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		stack[var1] = var2;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
		this.stack = new ItemStack[this.getSizeInventory()];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte("Slot");
			if (var5 >= 0 && var5 < this.stack.length) {
				this.stack[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
		this.generatorBurnTime = par1NBTTagCompound.getShort("generatorBurnTime");
		this.maxBurnTime = par1NBTTagCompound.getShort("maxBurnTime");
		this.power = par1NBTTagCompound.getShort("power");
		this.maxpower = par1NBTTagCompound.getShort("maxpower");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("generatorBurnTime", (short) this.generatorBurnTime);
		par1NBTTagCompound.setShort("maxBurnTime", (short) this.maxBurnTime);
		par1NBTTagCompound.setShort("power", (short) this.power);
		par1NBTTagCompound.setShort("maxpower", (short) this.maxpower);
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.stack.length; ++var3) {
			if (this.stack[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.stack[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Items", var2);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (!this.worldObj.isRemote) {
			detectOnLoad();
			if (generatorBurnTime > 0) {
				if (generatorBurnTime > GenerateSpeed) {
					if (power + (GenerateSpeed / 5) * 2 < maxpower) {
						power += (GenerateSpeed / 5) * 2;
					} else {
						power = maxpower;
					}
					generatorBurnTime -= (GenerateSpeed / 5) * 2;
				} else {
					if (power + generatorBurnTime < maxpower) {
						power += generatorBurnTime;
					} else {
						power = maxpower;
					}
					generatorBurnTime = 0;
				}
			} else {
				ItemStack burnItem = getStackInSlot(0);
				int getBurnTime = TileEntityFurnace.getItemBurnTime(burnItem);
				if (getBurnTime > 0 && power + (float) (getBurnTime / 5) * 2 < maxpower) {
					maxBurnTime = getBurnTime;
					generatorBurnTime = getBurnTime;
					if (burnItem.getItem() == Items.lava_bucket) {
						setInventorySlotContents(0, new ItemStack(Items.bucket, 1));
					} else {
						if (burnItem.stackSize - 1 > 0) {
							burnItem.stackSize--;
							setInventorySlotContents(0, burnItem);
						} else {
							setInventorySlotContents(0, null);
						}
					}
				}
				Generator.updateBlockState(generatorBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			if (power > GenerateSpeed && connected.size() > 0) {
				ArrayList<ElectricStorageEntity> emptyconnection = new ArrayList<ElectricStorageEntity>();
				for (ElectricStorageEntity component : connected.toArray(new ElectricStorageEntity[0])) {
					if (component.power < component.maxpower) {
						emptyconnection.add(component);
					}
				}

				if (emptyconnection.size() > 0) {
					for (ElectricStorageEntity component : emptyconnection.toArray(new ElectricStorageEntity[0])) {
						component.addPower(GenerateSpeed / emptyconnection.size());
					}

					power -= GenerateSpeed;
				}
			}
		}
	}

	@Override
	public void NeighborBlockCreated(TileEntity which, int direction) {
		// TODO Auto-generated method stub
		try {
			ElectricStorageEntity cableEntity = (ElectricStorageEntity) which;
			connected.add(cableEntity);
		} catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
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
}
