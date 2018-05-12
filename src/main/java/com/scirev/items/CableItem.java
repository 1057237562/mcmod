package com.scirev.items;

import com.scirev.blocks.container.functional.Cable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CableItem extends Item {

	private Block bindBlock;
	private int meta;

	public CableItem(Block bind, int metadata) {
		// TODO Auto-generated constructor stub
		bindBlock = bind;
		meta = metadata;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side,
	        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		// TODO Auto-generated method stub

		//System.out.println(x + "," + y + "," + z + "," + side);
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
		if (world.setBlock(x, y, z, bindBlock)) {
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			world.getBlock(x, y, z).createTileEntity(world, meta);
			world.getBlock(x, y, z).onPostBlockPlaced(world, x, y, z, side);
			((Cable) world.getBlock(x, y, z)).meta = meta;
			return true;
		}

		return false;
	}
}
