package com.scirev.blocks.nature;

import java.util.List;
import java.util.Random;

import com.scirev.SciRevolution;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GeneralLeaf extends BlockLeaves {

	public static final String[][] leavetype = new String[][] { { "rubber" }, { "rubber_opaque" } };

	@Override
	public IIcon getIcon(int side, int meta) {
		// TODO Auto-generated method stub
		return (meta & 3) == 1 ? this.field_150129_M[this.field_150127_b][1]
		        : this.field_150129_M[this.field_150127_b][0];
	}

	@Override
	public String[] func_150125_e() {
		// TODO Auto-generated method stub
		return GeneralLog.logs;
	}

	protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_,
	        int p_150124_6_) {
		/*if ((p_150124_5_ & 3) == 1 && p_150124_1_.rand.nextInt(p_150124_6_) == 0) {
			this.dropBlockAsItem(p_150124_1_, p_150124_2_, p_150124_3_, p_150124_4_, new ItemStack(Items.apple, 1, 0));
		}*/
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int p_149692_1_) {
		return super.damageDropped(p_149692_1_) + 4;
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_) {
		return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) & 3;
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		for (int i = 0; i < GeneralLog.logs.length; i++) {
			p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		for (int i = 0; i < leavetype.length; ++i) {
			this.field_150129_M[i] = new IIcon[leavetype[i].length];

			for (int j = 0; j < leavetype[i].length; ++j) {
				this.field_150129_M[i][j] = p_149651_1_.registerIcon(getTextureName() + leavetype[i][j]);
			}
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_,
	        int p_149646_5_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(SciRevolution.generalsapling);
	}
}
