package com.scirev.blocks;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AnvilBlock extends BlockAnvil implements ITileEntityProvider {

	public AnvilBlock() {
		super();
		isBlockContainer = true;
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_,
	        EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub

		return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_,
		        p_149727_7_, p_149727_8_, p_149727_9_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}

}
