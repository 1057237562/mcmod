package com.scirev.blocks.container.functional.container;

import java.util.Iterator;

import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class GeneratorContainer extends Container {

	private int lastGeneratorBurnTime = 0;
	private int lastMaxBurnTime = 0;
	private int lastPower = 0;
	private int lastMaxPower = 0;
	private GeneratorEntity tile;

	public GeneratorContainer(InventoryPlayer par1InventoryPlayer, GeneratorEntity entity) {
		// TODO Auto-generated constructor stub
		addSlotToContainer(new Slot(entity, 0, 65, 36));

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
		par1iCrafting.sendProgressBarUpdate(this, 0, this.tile.generatorBurnTime);
		par1iCrafting.sendProgressBarUpdate(this, 1, this.tile.maxBurnTime);
		par1iCrafting.sendProgressBarUpdate(this, 2, this.tile.power);
		par1iCrafting.sendProgressBarUpdate(this, 3, this.tile.maxpower);
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
			if (par2 == 0) {
				if (!this.mergeItemStack(var5, 1, 37, false)) {
					return null;
				}
				var4.onSlotChange(var5, var3);
			} else if (par2 >= 1 && par2 < 37) {
				if (TileEntityFurnace.isItemFuel(var5)) {
					if (!this.mergeItemStack(var5, 0, 1, false)) {
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
			this.tile.generatorBurnTime = par2;
		}
		if (par1 == 1) {
			this.tile.maxBurnTime = par2;
		}
		if (par1 == 2) {
			this.tile.power = par2;
		}
		if (par1 == 3) {
			this.tile.maxpower = par2;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		Iterator var1 = this.crafters.iterator();
		while (var1.hasNext()) {
			ICrafting var2 = (ICrafting) var1.next();

			if (this.lastGeneratorBurnTime != this.tile.generatorBurnTime) {
				var2.sendProgressBarUpdate(this, 0, this.tile.generatorBurnTime);
			}

			if (this.lastMaxBurnTime != this.tile.maxBurnTime) {
				var2.sendProgressBarUpdate(this, 1, this.tile.maxBurnTime);
			}
			if (this.lastPower != this.tile.power) {
				var2.sendProgressBarUpdate(this, 2, this.tile.power);
			}

			if (this.lastMaxPower != this.tile.maxpower) {
				var2.sendProgressBarUpdate(this, 3, this.tile.maxpower);
			}
		}
		this.lastGeneratorBurnTime = this.tile.generatorBurnTime;
		this.lastMaxBurnTime = this.tile.maxBurnTime;
		this.lastPower = this.tile.power;
		this.lastMaxPower = this.tile.maxpower;
	}

}
