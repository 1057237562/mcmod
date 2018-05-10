package com.scirev.blocks.container.functional.tileentity;

import com.scirev.blocks.container.functional.Extrusioner;
import com.scirev.electrical.ElectricNetwork;
import com.scirev.recipe.ExtrusionerCraftingRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ExtrusionerTileEntity extends ElectricStorageEntity implements IInventory {

	private ItemStack stack[] = new ItemStack[3];
	public int progress = 0;

	public ExtrusionerTileEntity() {
		super(32, 512, true);
		// TODO Auto-generated constructor stub
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
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (!worldObj.isRemote) {
			if (ElectricNetwork.getInstance().getPower(this) > 0) {
				if (canProgress()) {
					progress++;
					ElectricNetwork.getInstance().removePower(this, 1);
					if (this.progress == 200) {
						makingItem();
						progress = 0;
					}
				} else {
					progress = 0;
				}
				Extrusioner.updateBlockState(progress > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	private boolean canProgress() {
		if (this.stack[0] == null) {
			return false;
		} else {
			ItemStack itemstack = ExtrusionerCraftingRecipe.getOutput(this.stack[0]);
			if (itemstack == null)
				return false;
			if (this.stack[2] == null)
				return true;
			if (!this.stack[2].isItemEqual(itemstack))
				return false;
			int result = stack[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.stack[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
		}
	}

	private void makingItem() {
		ItemStack itemstack = ExtrusionerCraftingRecipe.getOutput(this.stack[0]);

		if (this.stack[2] == null) {
			this.stack[2] = itemstack.copy();
		} else if (this.stack[2].getItem() == itemstack.getItem()) {
			this.stack[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
		}

		this.stack[0].stackSize -= ExtrusionerCraftingRecipe.getInputStack(this.stack[0]).stackSize;

		if (this.stack[0].stackSize <= 0) {
			this.stack[0] = null;
		}
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
		this.maxpower = par1NBTTagCompound.getShort("maxpower");
		this.progress = par1NBTTagCompound.getShort("progress");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("maxpower", (short) this.maxpower);
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
}
