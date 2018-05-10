package com.scirev.blocks.container.functional.tileentity;

import com.scirev.electrical.ElectricNetwork;

public class LatheBottomShellEntity extends ElectricStorageEntity {

	public LatheManipulatePanelEntity entity;

	public LatheBottomShellEntity() {
		super(64, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (entity != null && ElectricNetwork.getInstance().getPower(entity) < entity.maxpower
		        && ElectricNetwork.getInstance().getPower(this) > 0) {
			ElectricNetwork.getInstance().removePower(this, 1);
			ElectricNetwork.getInstance().addPower(entity, 1);
		}
	}

}
