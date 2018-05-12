package com.scirev.blocks.container.functional;

import java.util.Random;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.FunctionalEntity;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Generator extends BlockContainer {

	IIcon top;
	IIcon bottom;
	IIcon side;
	IIcon front;

	boolean lit = false;
	private final Random random = new Random();
	static boolean normalreplace = false;

	public Generator(boolean light) {
		super(Material.iron);

		lit = light;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_,
	        float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// TODO Auto-generated method stub
		player.openGui(SciRevolution.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new GeneratorEntity();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int direction) {
		return side == 1 ? this.top : (side == 0 ? this.bottom : (side != direction ? this.blockIcon : this.front));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		this.blockIcon = r.registerIcon("scirev:generator_side");
		this.front = r.registerIcon(this.lit ? "scirev:generator_front_on" : "scirev:generator_front_off");
		this.top = r.registerIcon("scirev:generator_top");
		this.bottom = r.registerIcon("scirev:generator_bottom");
	}

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

	public static void updateBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		// TODO Auto-generated method stub
		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

		normalreplace = true;
		if (b) {
			worldObj.setBlock(xCoord, yCoord, zCoord, SciRevolution.lit_gen);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord, SciRevolution.gen);
		}
		normalreplace = false;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_,
	        int p_149749_6_) {
		// TODO Auto-generated method stub
		if (!normalreplace) {
			GeneratorEntity entity = (GeneratorEntity) world.getTileEntity(x, y, z);

			entity.onBlockBroke(world, x, y, z);

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
		}
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int p_149714_5_) {
		// TODO Auto-generated method stub
		FunctionalEntity Entity = (FunctionalEntity) world.getTileEntity(x, y, z);
		Entity.onBlockPlaced(world, x, y, z);

		super.onPostBlockPlaced(world, x, y, z, p_149714_5_);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(SciRevolution.gen);
	}
}
