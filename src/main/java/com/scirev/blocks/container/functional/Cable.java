package com.scirev.blocks.container.functional;

import javax.vecmath.Vector3d;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.CableEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Cable extends BlockContainer {

	Vector3d vector3d = new Vector3d();
	public int meta = 0;

	public Cable() {
		super(new Material(MapColor.ironColor));
		setBlockName("Cable");
		// TODO Auto-generated constructor stub
		float pixel = 1F / 16F;
		setBlockBounds(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2,
		        1 - 10 * pixel / 2);
		useNeighborBrightness = true;
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

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new CableEntity();//Reset
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int p_149714_5_) {
		// TODO Auto-generated method stub
		CableEntity cableEntity = (CableEntity) world.getTileEntity(x, y, z);
		cableEntity.onBlockPlaced(world, x, y, z);

		super.onPostBlockPlaced(world, x, y, z, p_149714_5_);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		// TODO Auto-generated method stub
		CableEntity cableEntity = (CableEntity) world.getTileEntity(x, y, z);
		cableEntity.onBlockBroke(world, x, y, z);

		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z,
	        int p_149690_5_, float p_149690_6_, int p_149690_7_) {
		// TODO Auto-generated method stub
		if (!world.isRemote && !world.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
		{
			this.dropBlockAsItem(world, x, y, z, new ItemStack(getItemDropped(), quantityDropped()));
		}
	}

	@Override
	protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_,
	        ItemStack p_149642_5_) {
		if (!p_149642_1_.isRemote && p_149642_1_.getGameRules().getGameRuleBooleanValue("doTileDrops")
		        && !p_149642_1_.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
		{
			if (captureDrops.get()) {
				capturedDrops.get().add(p_149642_5_);
				return;
			}
			float f = 0.7F;
			double d0 = (double) (p_149642_1_.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d1 = (double) (p_149642_1_.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (p_149642_1_.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(p_149642_1_, (double) p_149642_2_ + d0, (double) p_149642_3_ + d1,
			        (double) p_149642_4_ + d2, p_149642_5_);
			entityitem.delayBeforeCanPickup = 10;
			p_149642_1_.spawnEntityInWorld(entityitem);
		}
	}

	public Item getItemDropped() {
		return meta == 1 ? SciRevolution.alcable : meta == 2 ? SciRevolution.coppercable : null;
	}

	public int quantityDropped() {
		return 1;
	}

	@Override
	protected boolean canSilkHarvest() {
		// TODO Auto-generated method stub
		return false;
	}

}
