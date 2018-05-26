package com.scirev.blocks.container.functional.tileentity;

import com.scirev.recipe.ForgeMachineCraftingRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class AnvilEntity extends TileEntity implements IInventory {

	public ItemStack stack[] = new ItemStack[3];
	public int process = 0;

	public AnvilEntity() {
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
		this.process = par1NBTTagCompound.getShort("process");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
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
		par1NBTTagCompound.setShort("process", (short) process);
	}

	public boolean canProgress() {
		if (this.stack[0] == null && this.stack[1] == null) {
			return false;
		} else {
			ItemStack itemstack = this.stack[0] != null ? ForgeMachineCraftingRecipe.getOutput(this.stack[0])
			        : ForgeMachineCraftingRecipe.getOutput(this.stack[1]);
			if (itemstack == null)
				return false;
			if (this.stack[1] == null && this.stack[2] == null)
				return true;
			if (this.stack[2] != null && !this.stack[2].isItemEqual(itemstack)
			        && this.stack[1] != null && !this.stack[1].isItemEqual(stack[0]))
				return false;
			if (this.stack[2] == null)
				return true;
			int result = stack[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.stack[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
		}
	}

	public void Process() {
		if (stack[0] != null) {
			this.stack[1] = new ItemStack(stack[0].getItem());

			this.stack[0].stackSize -= ForgeMachineCraftingRecipe.getInputStack(this.stack[0]).stackSize;

			if (this.stack[0].stackSize <= 0) {
				this.stack[0] = null;
			}
		}
	}

	public void makingItem() {
		ItemStack itemstack = ForgeMachineCraftingRecipe.getOutput(this.stack[1]);

		if (this.stack[2] == null) {
			this.stack[2] = itemstack.copy();
		} else if (this.stack[2].getItem() == itemstack.getItem()) {
			this.stack[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
		}

		this.stack[1].stackSize -= ForgeMachineCraftingRecipe.getInputStack(this.stack[1]).stackSize;

		if (this.stack[1].stackSize <= 0) {
			this.stack[1] = null;
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		// TODO Auto-generated method stub
		readFromNBT(pkt.func_148857_g());
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public Packet getDescriptionPacket() {
		// TODO Auto-generated method stub
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, tag);
	}
}
