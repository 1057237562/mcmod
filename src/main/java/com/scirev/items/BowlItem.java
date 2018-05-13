package com.scirev.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BowlItem extends Item {
	
	private Block bindBlock;

	public BowlItem(Block bindBlock) {
		this.bindBlock = bindBlock;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side,
	        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		// TODO Auto-generated method stub
		switch (side) {
			case 0:
				if (createBlock(world, x, y - 1, z, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
			case 1:
				if (createBlock(world, x, y + 1, z, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
			case 2:
				if (createBlock(world, x, y, z - 1, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
			case 3:
				if (createBlock(world, x, y, z + 1, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
			case 4:
				if (createBlock(world, x - 1, y, z, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
			case 5:
				if (createBlock(world, x + 1, y, z, side)) {
					itemStack.stackSize--;
					return true;
				}
			break;
		}
		return false;
	}

	public boolean createBlock(World world, int x, int y, int z, int side) {
		if (world.getBlock(x, y, z) == Blocks.air && world.setBlock(x, y, z, bindBlock)) {
			world.getBlock(x, y, z).createTileEntity(world, 0);
			world.getBlock(x, y, z).onPostBlockPlaced(world, x, y, z, side);
			return true;
		}

		return false;
	}
}
