package com.scirev.blocks.container.functional;

import javax.vecmath.Vector3d;

import com.scirev.blocks.container.functional.tileentity.CableEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Cable extends BlockContainer {

	Vector3d vector3d = new Vector3d();

	public Cable() {
		super(Material.iron);
		setBlockName("Cable");
		// TODO Auto-generated constructor stub
		float pixel = 1F / 16F;
		setBlockBounds(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2,
		        1 - 10 * pixel / 2);
		useNeighborBrightness = true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
	        EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub

		((CableEntity) world.getTileEntity(x, y, z)).debug = true;
		System.out.println(((CableEntity) world.getTileEntity(x, y, z)).getPower());
		return true;
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
	public void onPostBlockPlaced(World world, int x, int y, int z,
	        int p_149714_5_) {
		// TODO Auto-generated method stub
		CableEntity cableEntity = (CableEntity) world.getTileEntity(x, y, z);
		cableEntity.onBlockPlaced(world, x, y, z);
		
		super.onPostBlockPlaced(world, x, y, z, p_149714_5_);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_,
	        int p_149749_6_) {
		// TODO Auto-generated method stub
		CableEntity cableEntity = (CableEntity) world.getTileEntity(x, y, z);
		cableEntity.onBlockBroke(world, x, y, z);
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

}
