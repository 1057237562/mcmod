package com.scirev.blocks.container.functional.tileentity;

import com.scirev.blocks.container.functional.LatheManipulatePanel;
import com.scirev.electrical.ElectricNetwork;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class LatheManipulatePanelEntity extends ElectricStorageEntity implements IInventory {

	private ItemStack stack[] = new ItemStack[3];
	public int progress = 0;
	public LatheBottomShellEntity bottomShell;
	public LathePowerSourceEntity powerSource;

	boolean checkMuiltBlock = false;

	public LatheManipulatePanelEntity() {
		super(64, true);
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
			if (!checkMuiltBlock) {
				LatheManipulatePanel.checkMuiltBlock(worldObj, xCoord, yCoord, zCoord);
				checkMuiltBlock = true;
			}
			if (ElectricNetwork.getInstance().getPower(this) > 0 && bottomShell != null && powerSource != null) {

			}
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
