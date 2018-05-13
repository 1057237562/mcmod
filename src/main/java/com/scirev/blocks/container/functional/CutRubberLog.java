package com.scirev.blocks.container.functional;

import java.util.Random;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.RubberEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CutRubberLog extends BlockContainer {

	IIcon top;
	IIcon bottom;
	IIcon side;
	IIcon front;

	private boolean hasRubber;

	public CutRubberLog(boolean hasRubber) {
		super(Material.wood);
		this.hasRubber = hasRubber;
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int direction) {
		return side == 1 ? this.top : (side == 0 ? this.bottom : (side != direction ? this.blockIcon : this.front));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		this.blockIcon = r.registerIcon("scirev:logrubber");
		this.front = r.registerIcon(hasRubber ? "scirev:logrubbercut" : "scirev:logrubbercut_norubber");
		this.top = r.registerIcon("scirev:logrubber_top");
		this.bottom = r.registerIcon("scirev:logrubber_top");
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}
	
	public static void updateBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		// TODO Auto-generated method stub
		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

		//normalreplace = true;
		if (b) {
			worldObj.setBlock(xCoord, yCoord, zCoord, SciRevolution.cutrubberlog);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord, SciRevolution.norubberlog);
		}
		//normalreplace = false;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		// TODO Auto-generated method stub
		return super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new RubberEntity();
	}
}
