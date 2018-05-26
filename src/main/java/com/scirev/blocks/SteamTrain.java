package com.scirev.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SteamTrain extends Block {

	IIcon top;
	IIcon bottom;
	IIcon back;
	IIcon left;
	IIcon right;

	public SteamTrain() {
		super(Material.iron);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int direction) {
		return side == 1 ? this.top
		        : (side == 0 ? this.bottom
		                : (side != direction
		                        ? (side != (direction + 1 < 6 ? direction + 1 : 2)
		                                ? (side != (direction + 3 < 6 ? direction + 3 : 4) ? left : right)
		                                : this.back)
		                        : this.bottom));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		this.left = r.registerIcon("scirev:steamtrain/steamtrain_left");
		this.right = r.registerIcon("scirev:steamtrain/steamtrain_right");
		this.back = r.registerIcon("scirev:steamtrain/steamtrain_back");
		this.top = r.registerIcon("scirev:steamtrain/steamtrain_top");
		this.bottom = r.registerIcon("scirev:steamtrain/steamtrain_bottom");
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_,
	        EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
		}

		if (l == 1) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
		}

		if (l == 2) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
		}

		if (l == 3) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
		}
	}

}
