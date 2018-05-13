package com.scirev.blocks.nature;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LeafItems extends ItemBlock {

	public LeafItems(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		// TODO Auto-generated method stub
		int i = stack.getItemDamage();
		if (i < 0 || i >= GeneralLog.logs.length) {
			i = 0;
		}

		return super.getUnlocalizedName() + "." + GeneralLog.logs[i];
	}

	@Override
	public int getMetadata(int meta) {
		// TODO Auto-generated method stub
		return meta;
	}
}
