package com.scirev.blocks.container.functional.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class KineticEntity extends TileEntity {

	public int powerhas;

	public KineticEntity() {

	}

	public void Recognition() {

	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		// TODO Auto-generated method stub
		nbtTagCompound.setShort("powerhas", (short) powerhas);
		super.writeToNBT(nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		// TODO Auto-generated method stub
		super.readFromNBT(nbtTagCompound);
		powerhas = nbtTagCompound.getShort("powerhas");
	}
}
