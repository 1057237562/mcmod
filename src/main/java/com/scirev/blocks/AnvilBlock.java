package com.scirev.blocks;

import java.util.Random;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.AnvilEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AnvilBlock extends BlockAnvil implements ITileEntityProvider {

	private final Random random = new Random();

	public AnvilBlock() {
		super();
		isBlockContainer = true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
	        float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub
		if (player.isSneaking()) {
			player.openGui(SciRevolution.instance, 8, world, x, y, z);
			return true;
		} else if (player.getHeldItem() != null && player.getHeldItem().getItem() == SciRevolution.hammer) {
			world.markBlockForUpdate(x, y, z);
			AnvilEntity anvilEntity = (AnvilEntity) world.getTileEntity(x, y, z);
			if (anvilEntity.canProgress()) {
				if (anvilEntity.stack[1] == null) {
					anvilEntity.Process();
				} else {
					anvilEntity.process++;
					world.playSound(x, y, z, "random.anvil_land", 0.2f, 1f, true);
					if (anvilEntity.process == 20) {
						anvilEntity.makingItem();
						anvilEntity.process = 0;
					}
				}

				return true;
			}
			return false;
		}else {
			return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		// TODO Auto-generated method stub
		AnvilEntity entity = (AnvilEntity) world.getTileEntity(x, y, z);

		if (entity != null) {
			for (int i1 = 0; i1 < entity.getSizeInventory(); ++i1) {
				ItemStack itemstack = entity.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					float f2 = this.random.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world, (double) ((float) x + f),
						        (double) ((float) y + f1), (double) ((float) z + f2),
						        new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
							        .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, p_149749_5_);
		}

		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new AnvilEntity();
	}

}
