package com.scirev.blocks.container.functional.tileentity;

import com.scirev.blocks.container.functional.BlastFurnace;
import com.scirev.recipe.BlastFurnaceRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class BlastFurnaceEntity extends TileEntity implements IInventory {

	private ItemStack stack[] = new ItemStack[4];
	public int BurnTime = 0;
	public int maxBurnTime = 0;
	public int progress = 0;

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
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return true;
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
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
		this.BurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.maxBurnTime = par1NBTTagCompound.getShort("maxBurnTime");
		this.progress = par1NBTTagCompound.getShort("progress");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.BurnTime);
		par1NBTTagCompound.setShort("maxBurnTime", (short) this.maxBurnTime);
		par1NBTTagCompound.setShort("progress", (short) this.progress);
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
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (!worldObj.isRemote) {
			if (BurnTime > 0) {
				BurnTime--;
				if (canSmelt()) {
					progress++;
					if (this.progress == 200) {
						makingItem();
						progress = 0;
					}
				}
			} else {
				if (canSmelt()) {
					ItemStack burnItem = getStackInSlot(1);
					int getBurnTime = TileEntityFurnace.getItemBurnTime(burnItem);
					if (getBurnTime > 0) {
						maxBurnTime = getBurnTime;
						BurnTime = getBurnTime;
						if (burnItem.getItem() == Items.lava_bucket) {
							setInventorySlotContents(1, new ItemStack(Items.bucket, 1));
						} else {
							if (burnItem.stackSize - 1 > 0) {
								burnItem.stackSize--;
								setInventorySlotContents(1, burnItem);
							} else {
								setInventorySlotContents(1, null);
							}
						}
					}
				}
				BlastFurnace.updateBlockState(BurnTime > 0, this.worldObj, this.xCoord, this.yCoord,
				        this.zCoord);
			}
		}
	}

	private boolean canSmelt() {
		if (this.stack[0] == null || stack[2] == null) {
			return false;
		} else {
			ItemStack itemstack = BlastFurnaceRecipe.getOutput(this.stack[0], stack[2]);
			if (itemstack == null)
				return false;
			if (this.stack[3] == null)
				return true;
			if (!this.stack[3].isItemEqual(itemstack))
				return false;
			int result = stack[3].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.stack[3].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
		}
	}

	private void makingItem() {
		ItemStack itemstack = BlastFurnaceRecipe.getOutput(this.stack[0], this.stack[2]);

		if (this.stack[3] == null) {
			this.stack[3] = itemstack.copy();
		} else if (this.stack[3].getItem() == itemstack.getItem()) {
			this.stack[3].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
		}

		this.stack[0].stackSize -= BlastFurnaceRecipe.getInputStack(this.stack[0], 0).stackSize;
		stack[2].stackSize -= BlastFurnaceRecipe.getInputStack(stack[2], 1).stackSize;

		if (this.stack[0].stackSize <= 0) {
			this.stack[0] = null;
		}
		if (this.stack[2].stackSize <= 0) {
			this.stack[2] = null;
		}
	}
}
