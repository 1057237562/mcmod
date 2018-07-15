package com.scirev.entity;

import com.scirev.SciRevolution;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

public class SteamTrainEntity extends EntityMinecart implements IInventory {

	private ItemStack[] minecartContainerItems = new ItemStack[1];
	private boolean dropContentsWhenDead = true;

	public int generatorBurnTime = 0;
	public int maxBurnTime = 0;

	public int waterstorage = 0;
	public static int maxwater = 1000;
	public int GenerateSpeed = 10;
	boolean refresh;
	int Ticks = 0;
	public double propsalpushX;
	public double propsalpushZ;
	public double pushX;
	public double pushZ;
	private final float speed = 8f;
	private boolean transmitted = false;

	public SteamTrainEntity(World p_i1718_1_) {
		super(p_i1718_1_);
		// TODO Auto-generated constructor stub
	}

	public SteamTrainEntity(World p_i1719_1_, double p_i1719_2_, double p_i1719_4_, double p_i1719_6_) {
		super(p_i1719_1_, p_i1719_2_, p_i1719_4_, p_i1719_6_);
	}

	@Override
	public int getMinecartType() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	public boolean isPoweredCart() {
		// TODO Auto-generated method stub
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	protected void setMinecartPowered(boolean p_94107_1_) {
		if (p_94107_1_) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (this.dataWatcher.getWatchableObjectByte(16) | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (this.dataWatcher.getWatchableObjectByte(16) & -2)));
		}
	}

	@Override
	public Block func_145817_o() {
		// TODO Auto-generated method stub
		return SciRevolution.steamtrain;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player)))
			return true;
		this.propsalpushX = Math.signum(this.posX - player.posX);
		this.propsalpushZ = Math.signum(this.posZ - player.posZ);
		transmitted = false;
		return true;
	}

	public void killMinecart(DamageSource p_94095_1_) {
		super.killMinecart(p_94095_1_);

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			ItemStack itemstack = this.getStackInSlot(i);

			if (itemstack != null) {
				float f = this.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

				while (itemstack.stackSize > 0) {
					int j = this.rand.nextInt(21) + 10;

					if (j > itemstack.stackSize) {
						j = itemstack.stackSize;
					}

					itemstack.stackSize -= j;
					EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double) f,
					        this.posY + (double) f1, this.posZ + (double) f2,
					        new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
					float f3 = 0.05F;
					entityitem.motionX = (double) ((float) this.rand.nextGaussian() * f3);
					entityitem.motionY = (double) ((float) this.rand.nextGaussian() * f3 + 0.2F);
					entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * f3);
					this.worldObj.spawnEntityInWorld(entityitem);
				}
			}
		}
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int p_70301_1_) {
		return this.minecartContainerItems[p_70301_1_];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		if (this.minecartContainerItems[p_70298_1_] != null) {
			ItemStack itemstack;

			if (this.minecartContainerItems[p_70298_1_].stackSize <= p_70298_2_) {
				itemstack = this.minecartContainerItems[p_70298_1_];
				this.minecartContainerItems[p_70298_1_] = null;
				return itemstack;
			} else {
				itemstack = this.minecartContainerItems[p_70298_1_].splitStack(p_70298_2_);

				if (this.minecartContainerItems[p_70298_1_].stackSize == 0) {
					this.minecartContainerItems[p_70298_1_] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		if (this.minecartContainerItems[p_70304_1_] != null) {
			ItemStack itemstack = this.minecartContainerItems[p_70304_1_];
			this.minecartContainerItems[p_70304_1_] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		this.minecartContainerItems[p_70299_1_] = p_70299_2_;

		if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
			p_70299_2_.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
	 * hasn't changed and skip it.
	 */
	public void markDirty() {
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return this.isDead ? false : p_70300_1_.getDistanceSqToEntity(this) <= 64.0D;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.func_95999_t() : "container.minecart";
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Teleports the entity to another dimension. Params: Dimension number to teleport to
	 */
	public void travelToDimension(int p_71027_1_) {
		this.dropContentsWhenDead = false;
		super.travelToDimension(p_71027_1_);
	}

	/**
	 * Will get destroyed next tick.
	 */
	public void setDead() {
		if (this.dropContentsWhenDead) {
			for (int i = 0; i < this.getSizeInventory(); ++i) {
				ItemStack itemstack = this.getStackInSlot(i);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j = this.rand.nextInt(21) + 10;

						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}

						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double) f,
						        this.posY + (double) f1, this.posZ + (double) f2,
						        new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
							        .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * f3);
						this.worldObj.spawnEntityInWorld(entityitem);
					}
				}
			}
		}

		super.setDead();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		p_70014_1_.setBoolean("Transmit", this.transmitted);
		p_70014_1_.setDouble("PropX", this.propsalpushX);
		p_70014_1_.setDouble("PropZ", this.propsalpushZ);
		p_70014_1_.setDouble("PushX", this.pushX);
		p_70014_1_.setDouble("PushZ", this.pushZ);
		p_70014_1_.setShort("generatorBurnTime", (short) this.generatorBurnTime);
		p_70014_1_.setShort("maxBurnTime", (short) this.maxBurnTime);
		p_70014_1_.setShort("water", (short) this.waterstorage);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.minecartContainerItems.length; ++i) {
			if (this.minecartContainerItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.minecartContainerItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		p_70014_1_.setTag("Items", nbttaglist);
		super.writeEntityToNBT(p_70014_1_);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		super.readEntityFromNBT(p_70037_1_);
		NBTTagList nbttaglist = p_70037_1_.getTagList("Items", 10);
		this.minecartContainerItems = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.minecartContainerItems.length) {
				this.minecartContainerItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.generatorBurnTime = p_70037_1_.getShort("generatorBurnTime");
		this.maxBurnTime = p_70037_1_.getShort("maxBurnTime");
		this.waterstorage = p_70037_1_.getShort("water");
		this.propsalpushX = p_70037_1_.getDouble("PropX");
		this.propsalpushZ = p_70037_1_.getDouble("PropZ");
		this.pushX = p_70037_1_.getDouble("PushX");
		this.pushZ = p_70037_1_.getDouble("PushZ");
		this.transmitted = p_70037_1_.getBoolean("Transmit");
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		super.onUpdate();
		if (!this.worldObj.isRemote) {
			//detectOnLoad();
			if (generatorBurnTime > 0) {
				generatorBurnTime--;
				if (Ticks == 4) {
					if (waterstorage != 0) {
						waterstorage--;
						if (!transmitted) {
							pushX = Math.signum(propsalpushX);
							pushZ = Math.signum(propsalpushZ);
							transmitted = true;
						}
					}else {
						pushX = pushZ = 0D;
					}
					Ticks = 0;
				} else {
					Ticks++;
				}
			} else {
				pushX = pushZ = 0D;
				ItemStack burnItem = getStackInSlot(0);
				int getBurnTime = TileEntityFurnace.getItemBurnTime(burnItem);
				if (getBurnTime > 0 && waterstorage > 0) {
					transmitted = false;
					maxBurnTime = getBurnTime;
					generatorBurnTime = getBurnTime;
					if (burnItem.getItem() == Items.lava_bucket) {
						setInventorySlotContents(0, new ItemStack(Items.bucket, 1));
					} else {
						if (burnItem.stackSize - 1 > 0) {
							burnItem.stackSize--;
							setInventorySlotContents(0, burnItem);
						} else {
							setInventorySlotContents(0, null);
						}
					}
				}
			}

			this.setMinecartPowered(generatorBurnTime > 0 && waterstorage > 0);

			if (generatorBurnTime > 0 && waterstorage > 0 && this.rand.nextInt(2) == 0) {
				this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void applyDrag() {
		double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;

		if (d0 > 0) {
			d0 = (double) MathHelper.sqrt_double(d0);
			this.pushX /= d0;
			this.pushZ /= d0;
			double d1 = 0.05D * speed;
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.0D;
			this.motionZ *= 0.800000011920929D;
			this.motionX += this.pushX * d1;
			this.motionZ += this.pushZ * d1;
		} else {
			this.motionX *= 0.9800000190734863D;
			this.motionY *= 0.0D;
			this.motionZ *= 0.9800000190734863D;
		}

		super.applyDrag();
	}

	@Override
	protected void func_145821_a(int p_145821_1_, int p_145821_2_, int p_145821_3_, double p_145821_4_,
	        double p_145821_6_, Block p_145821_8_, int p_145821_9_) {
		super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_, p_145821_6_, p_145821_8_, p_145821_9_);
		double d2 = this.pushX * this.pushX + this.pushZ * this.pushZ;

		if (d2 > 1.0E-4D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D) {
			d2 = (double) MathHelper.sqrt_double(d2);
			this.pushX /= d2;
			this.pushZ /= d2;

			if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0D) {
				this.pushX = 0.0D;
				this.pushZ = 0.0D;
			} else {
				this.pushX = this.motionX;
				this.pushZ = this.motionZ;
			}
		}
	}

	@Override
	public int getDefaultDisplayTileData() {
		// TODO Auto-generated method stub
		return 2;
	}
}
