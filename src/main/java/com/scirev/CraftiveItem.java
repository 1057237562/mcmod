package com.scirev;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftiveItem extends Item {

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		if (itemStack.getMaxDamage() == itemStack.getItemDamage()) {
			return (ItemStack) null;
		} else {
			ItemStack itemStack2 = itemStack.copy();
			itemStack2.setItemDamage(itemStack.getItemDamage() + 1);
			return itemStack2;
		}
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}
}
