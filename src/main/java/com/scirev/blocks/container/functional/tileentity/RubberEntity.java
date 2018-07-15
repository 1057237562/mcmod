package com.scirev.blocks.container.functional.tileentity;

import com.scirev.blocks.container.functional.CutRubberLog;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class RubberEntity extends TileEntity {

	public int rubberRemains = 18;
	public int regenerateTicks = 0;

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (!worldObj.isRemote) {
			if (rubberRemains == 0) {
				regenerateTicks++;
				if (regenerateTicks == 750) {
					rubberRemains = 18;
					CutRubberLog.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);
				}
				CutRubberLog.updateBlockState(false, worldObj, xCoord, yCoord, zCoord);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		// TODO Auto-generated method stub
		nbtTag.setShort("remains", (short) rubberRemains);
		nbtTag.setShort("regenerateTicks", (short) regenerateTicks);
		super.writeToNBT(nbtTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		// TODO Auto-generated method stub
		regenerateTicks = nbtTag.getShort("regenerateTicks");
		rubberRemains = nbtTag.getShort("remains");
		super.readFromNBT(nbtTag);
	}

}
