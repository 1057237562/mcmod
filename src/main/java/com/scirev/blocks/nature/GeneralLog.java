package com.scirev.blocks.nature;

import java.util.List;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.CutRubberLog;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class GeneralLog extends BlockLog {
	public final static String[] logs = new String[] { "rubber" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		// TODO Auto-generated method stub
		for (int i = 0; i < logs.length; i++) {
			p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.field_150167_a = new IIcon[logs.length];
		this.field_150166_b = new IIcon[logs.length];

		for (int i = 0; i < field_150167_a.length; i++) {
			this.field_150167_a[i] = register.registerIcon(getTextureName() + logs[i]);
			this.field_150166_b[i] = register.registerIcon(getTextureName() + logs[i] + "_top");
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
	        float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub
		if (player.getHeldItem() != null) {
			switch (world.getBlockMetadata(x, y, z)) {
				case 0:
					if (player.getHeldItem().getItem() instanceof ItemSword) {
						world.setBlock(x, y, z, SciRevolution.cutrubberlog);
						((CutRubberLog) world.getBlock(x, y, z)).onBlockPlacedBy(world, x, y, z, player, null);
						player.getHeldItem().setItemDamage(player.getHeldItem().getItemDamage() + 1);
						if (player.getHeldItem().getItemDamage() == player.getHeldItem().getMaxDamage()) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						}
					}
				break;
			}
		}
		return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}

}
