package com.scirev.blocks.container.functional;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.SteamEngineEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SteamEngine extends BlockContainer {

	public SteamEngine() {
		super(Material.iron);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
	        float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub
		if (player.getHeldItem() == null ? false : player.getHeldItem().getItem() == Items.water_bucket) {
			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
			((SteamEngineEntity) world.getTileEntity(x, y, z)).waterstorage = SteamEngineEntity.maxwater;
		} else {
			player.openGui(SciRevolution.instance, 6, world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new SteamEngineEntity();
	}

	@Override
	public int getRenderType() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNormalCube() {
		// TODO Auto-generated method stub
		return false;
	}
}
