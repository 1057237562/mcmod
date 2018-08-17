package com.scirev.blocks;

import java.util.ArrayList;

import net.minecraft.block.BlockNewLeaf;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NewLeavesBlock extends BlockNewLeaf {

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int chance = this.func_150123_b(metadata);

		if (fortune > 0) {
			chance -= 2 << fortune;
			if (chance < 10)
				chance = 10;
		}

		if (world.rand.nextInt(chance) == 0)
			ret.add(new ItemStack(this.getItemDropped(metadata, world.rand, fortune), 1, this.damageDropped(metadata)));

		if (world.rand.nextInt(chance) == 1 || world.rand.nextInt(chance) == 2)
			ret.add(new ItemStack(Items.stick, 1));

		chance = 200;
		if (fortune > 0) {
			chance -= 10 << fortune;
			if (chance < 40)
				chance = 40;
		}

		this.captureDrops(true);
		this.func_150124_c(world, x, y, z, metadata, chance); // Dammet mojang
		ret.addAll(this.captureDrops(false));
		return ret;
	}

}
