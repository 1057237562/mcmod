package com.scirev.blocks.container.functional;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.BowlEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Bowl extends BlockContainer {

	public Bowl() {
		super(Material.wood);
		// TODO Auto-generated constructor stub
		float pixel = 1F / 16F;
		setBlockBounds(4 * pixel, 1 - 12 * pixel, 4 * pixel, 1 - 4 * pixel, 0, 1 - 4 * pixel);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new BowlEntity();
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
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_,
	        int p_149690_7_) {
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
		return Items.bowl;
	}

	public int quantityDropped() {
		return 1;
	}

	@Override
	protected boolean canSilkHarvest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
	        EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub
		if (world.getBlockMetadata(x, y, z) == 1) {
			/*EntityItem entityitem = new EntityItem(world, player.posX, player.posY, player.posZ,
			        new ItemStack(SciRevolution.rawrubber, 1));
			entityitem.delayBeforeCanPickup = 0;
			world.spawnEntityInWorld(entityitem);*/
			player.inventory.addItemStackToInventory(new ItemStack(SciRevolution.rawrubber, 1));
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
		return true;
	}

}
