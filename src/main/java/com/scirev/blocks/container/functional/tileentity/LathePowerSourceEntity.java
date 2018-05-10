package com.scirev.blocks.container.functional.tileentity;

public class LathePowerSourceEntity extends ElectricStorageEntity {

	public LatheManipulatePanelEntity entity;

	public LathePowerSourceEntity() {
		super(64, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		if (entity != null && entity.power < entity.maxpower && power > 0) {
			power -= 1;
			entity.power += 1;
		}
	}
}
