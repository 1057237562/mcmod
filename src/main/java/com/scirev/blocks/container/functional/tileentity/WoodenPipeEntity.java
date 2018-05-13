package com.scirev.blocks.container.functional.tileentity;

import com.scirev.SciRevolution;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class WoodenPipeEntity extends TileEntity {

	private boolean flowing = false;
	private int flowingTicks = 0;

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		try {
			if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == SciRevolution.bowl) {
				switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
					case 4:
						if (canHarvest(xCoord + 1, yCoord, zCoord)
						        && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) == 4) {
							flowing = true;
							flowingTicks++;
							testHarvest(xCoord + 1, yCoord, zCoord);
						}
					break;

					case 1:
						if (canHarvest(xCoord, yCoord, zCoord + 1)
						        && worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) == 2) {
							flowing = true;
							flowingTicks++;
							testHarvest(xCoord, yCoord, zCoord + 1);
						}
					break;
					case 2:
						if (canHarvest(xCoord - 1, yCoord, zCoord)
						        && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) == 5) {
							flowing = true;
							flowingTicks++;
							testHarvest(xCoord - 1, yCoord, zCoord);
						}
					break;
					case 3:
						if (canHarvest(xCoord, yCoord, zCoord - 1)
						        && worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) == 3) {
							flowing = true;
							flowingTicks++;
							testHarvest(xCoord, yCoord, zCoord - 1);
						}
					break;
				}
			} else {
				flowingTicks = 0;
				flowing = false;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			flowingTicks = 0;
			flowing = false;
		}
	}

	public boolean isFlowing() {
		// TODO Auto-generated method stub
		return flowing;
	}

	public boolean canHarvest(int x, int y, int z) {
		try {
			if (((RubberEntity) worldObj.getTileEntity(x, y, z)).rubberRemains > 0
			        && worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord) != 1) {
				return true;
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		flowingTicks = 0;
		flowing = false;
		return false;
	}

	public void testHarvest(int x, int y, int z) {
		if (flowingTicks == 200) {
			((RubberEntity) worldObj.getTileEntity(x, y, z)).rubberRemains--;
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord, 1, 2);
			flowing = false;
			flowingTicks = 0;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		// TODO Auto-generated method stub
		super.writeToNBT(nbtTag);
		nbtTag.setShort("flowingTicks", (short) flowingTicks);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		// TODO Auto-generated method stub
		flowingTicks = nbtTag.getShort("flowingTicks");
		super.readFromNBT(nbtTag);
	}
}
