package com.scirev.blocks.container.functional;

import com.scirev.blocks.container.functional.tileentity.WoodenPipeEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WoodenPipe extends BlockContainer {

	public WoodenPipe() {
		super(Material.wood);
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
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new WoodenPipeEntity();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		// TODO Auto-generated method stub

		float pixel = 1F / 16F;
		switch (world.getBlockMetadata(x, y, z)) {
			case 4:
				this.setBlockBounds(1, 12 * pixel / 2, 12 * pixel / 2, 1 - 16 * pixel / 2,
				        1 - 12 * pixel / 2,
				        1 - 12 * pixel / 2);
			break;
			case 1:
				this.setBlockBounds(12 * pixel / 2, 12 * pixel / 2, 1, 1 - 12 * pixel / 2,
				        1 - 12 * pixel / 2,
				        1 - 16 * pixel / 2);
			break;
			case 2:
				this.setBlockBounds(0, 12 * pixel / 2, 12 * pixel / 2, 1 - 16 * pixel / 2,
				        1 - 12 * pixel / 2,
				        1 - 12 * pixel / 2);
			break;
			case 3:
				this.setBlockBounds(12 * pixel / 2, 12 * pixel / 2, 0, 1 - 12 * pixel / 2,
				        1 - 12 * pixel / 2,
				        1 - 16 * pixel / 2);
			break;
		}
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
}
