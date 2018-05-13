package com.scirev.blocks.container.functional;

import com.scirev.blocks.container.functional.tileentity.FunctionalEntity;
import com.scirev.blocks.container.functional.tileentity.LatheBottomShellEntity;
import com.scirev.blocks.container.functional.tileentity.LatheManipulatePanelEntity;
import com.scirev.blocks.container.functional.tileentity.LathePowerSourceEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LatheManipulatePanel extends BlockContainer {

	public LatheManipulatePanel() {
		super(Material.iron);
		// TODO Auto-generated constructor stub
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
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
	        EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
		}

		if (l == 1) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
		}

		if (l == 2) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
		}

		if (l == 3) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		// TODO Auto-generated method stub
		checkMuiltBlock(world, x, y, z);
		super.onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new LatheManipulatePanelEntity();
	}

	public static void checkMuiltBlock(World world, int x, int y, int z) {
		LatheManipulatePanelEntity entity = (LatheManipulatePanelEntity) world.getTileEntity(x, y, z);
		entity.powerSource = null;
		entity.bottomShell = null;
		try {
			if ((LathePowerSourceEntity) world.getTileEntity(x + 1, y, z) != null
			        && (LatheBottomShellEntity) world.getTileEntity(x - 1, y, z) != null) {
				entity.bottomShell = (LatheBottomShellEntity) world.getTileEntity(x - 1, y, z);
				entity.powerSource = (LathePowerSourceEntity) world.getTileEntity(x + 1, y, z);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((LathePowerSourceEntity) world.getTileEntity(x - 1, y, z) != null
			        && (LatheBottomShellEntity) world.getTileEntity(x + 1, y, z) != null) {
				entity.bottomShell = (LatheBottomShellEntity) world.getTileEntity(x + 1, y, z);
				entity.powerSource = (LathePowerSourceEntity) world.getTileEntity(x - 1, y, z);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((LathePowerSourceEntity) world.getTileEntity(x, y, z + 1) != null
			        && (LatheBottomShellEntity) world.getTileEntity(x, y, z - 1) != null) {
				entity.bottomShell = (LatheBottomShellEntity) world.getTileEntity(x, y, z - 1);
				entity.powerSource = (LathePowerSourceEntity) world.getTileEntity(x, y, z + 1);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((LathePowerSourceEntity) world.getTileEntity(x, y, z - 1) != null
			        && (LatheBottomShellEntity) world.getTileEntity(x, y, z + 1) != null) {
				entity.bottomShell = (LatheBottomShellEntity) world.getTileEntity(x, y, z + 1);
				entity.powerSource = (LathePowerSourceEntity) world.getTileEntity(x, y, z - 1);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		if (entity.bottomShell != null) {
			entity.bottomShell.entity = entity;
			entity.bottomShell.getWorldObj().setBlockMetadataWithNotify(entity.bottomShell.xCoord,
			        entity.bottomShell.yCoord, entity.bottomShell.zCoord, entity.getBlockMetadata(), 2);
		}
		if (entity.powerSource != null) {
			entity.powerSource.entity = entity;
			entity.powerSource.getWorldObj().setBlockMetadataWithNotify(entity.powerSource.xCoord,
			        entity.powerSource.yCoord, entity.powerSource.zCoord, entity.getBlockMetadata(), 2);
		}
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int p_149714_5_) {
		// TODO Auto-generated method stub
		FunctionalEntity Entity = (FunctionalEntity) world.getTileEntity(x, y, z);
		Entity.onBlockPlaced(world, x, y, z);

		super.onPostBlockPlaced(world, x, y, z, p_149714_5_);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		// TODO Auto-generated method stub
		FunctionalEntity Entity = (FunctionalEntity) world.getTileEntity(x, y, z);
		Entity.onBlockBroke(world, x, y, z);
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}
}
