package com.scirev.blocks.container.functional.container;

import java.util.Iterator;

import com.scirev.blocks.container.functional.tileentity.ForgeMachineEntity;
import com.scirev.recipe.ForgeMachineCraftingRecipe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ForgeMachineContainer extends Container {

	private int lastProgress = 0;
	private ForgeMachineEntity tile;

	public ForgeMachineContainer(InventoryPlayer par1InventoryPlayer, ForgeMachineEntity entity) {
		// TODO Auto-generated constructor stub
		addSlotToContainer(new Slot(entity, 0, 56, 36));
		addSlotToContainer(new Slot(entity, 1, 116, 35));

		tile = entity;

		int var3;
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(
				        new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}
		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1iCrafting) {
		// TODO Auto-generated method stub
		super.addCraftingToCrafters(par1iCrafting);
		par1iCrafting.sendProgressBarUpdate(this, 0, this.tile.progress);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		if (var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (par2 >= 0 && par2 <= 2) {
				if (!this.mergeItemStack(var5, 2, 38, false)) {
					return null;
				}
				var4.onSlotChange(var5, var3);
			} else if (par2 >= 3 && par2 < 39) {
				if (ForgeMachineCraftingRecipe.isRecipeItem(var5.getItem())) {
					if (!this.mergeItemStack(var5, 0, 3, false)) {
						return null;
					}
				}
			}
			if (var5.stackSize == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}
			if (var5.stackSize == var5.stackSize) {
				return null;
			}
			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}
		return var3;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.tile.progress = par2;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		Iterator var1 = this.crafters.iterator();
		while (var1.hasNext()) {
			ICrafting var2 = (ICrafting) var1.next();

			if (this.lastProgress != this.tile.progress) {
				var2.sendProgressBarUpdate(this, 0, this.tile.progress);
			}
		}
		this.lastProgress = this.tile.progress;
	}

}