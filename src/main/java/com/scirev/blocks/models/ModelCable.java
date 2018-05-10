package com.scirev.blocks.models;

import com.scirev.blocks.container.functional.tileentity.FunctionalEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModelCable extends ModelBase {
	//fields
	ModelRenderer Cable;
	ModelRenderer CableNorth;
	ModelRenderer CableSouth;
	ModelRenderer CableWest;
	ModelRenderer CableEast;
	ModelRenderer CableUp;
	ModelRenderer CableDown;

	public ModelCable() {
		textureWidth = 64;
		textureHeight = 32;

		Cable = new ModelRenderer(this, 0, 0);
		Cable.addBox(0F, 0F, 0F, 6, 6, 6);
		Cable.setRotationPoint(-3F, 13F, -3F);
		Cable.setTextureSize(64, 32);
		Cable.mirror = true;
		setRotation(Cable, 0F, 0F, 0F);
		CableNorth = new ModelRenderer(this, 0, 0);
		CableNorth.addBox(0F, 0F, 0F, 6, 6, 6);
		CableNorth.setRotationPoint(-8F, 13F, -3F);
		CableNorth.setTextureSize(64, 32);
		CableNorth.mirror = true;
		setRotation(CableNorth, 0F, 0F, 0F);
		CableSouth = new ModelRenderer(this, 0, 0);
		CableSouth.addBox(0F, 0F, 0F, 6, 6, 6);
		CableSouth.setRotationPoint(2F, 13F, -3F);
		CableSouth.setTextureSize(64, 32);
		CableSouth.mirror = true;
		setRotation(CableSouth, 0F, 0F, 0F);
		CableWest = new ModelRenderer(this, 0, 0);
		CableWest.addBox(0F, 0F, 0F, 6, 6, 6);
		CableWest.setRotationPoint(-3F, 13F, -8F);
		CableWest.setTextureSize(64, 32);
		CableWest.mirror = true;
		setRotation(CableWest, 0F, 0F, 0F);
		CableEast = new ModelRenderer(this, 0, 0);
		CableEast.addBox(0F, 0F, 0F, 6, 6, 6);
		CableEast.setRotationPoint(-3F, 13F, 2F);
		CableEast.setTextureSize(64, 32);
		CableEast.mirror = true;
		setRotation(CableEast, 0F, 0F, 0F);
		CableUp = new ModelRenderer(this, 0, 0);
		CableUp.addBox(0F, 0F, 0F, 6, 6, 6);
		CableUp.setRotationPoint(-3F, 8F, -3F);
		CableUp.setTextureSize(64, 32);
		CableUp.mirror = true;
		setRotation(CableUp, 0F, 0F, 0F);
		CableDown = new ModelRenderer(this, 0, 0);
		CableDown.addBox(0F, 0F, 0F, 6, 6, 6);
		CableDown.setRotationPoint(-3F, 18F, -3F);
		CableDown.setTextureSize(64, 32);
		CableDown.mirror = true;
		setRotation(CableDown, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Cable.render(f5);

		World world = tile.getWorldObj();

		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord - 1, tile.yCoord, tile.zCoord) != null) {
				CableNorth.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord + 1, tile.yCoord, tile.zCoord) != null) {
				CableSouth.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord - 1) != null) {
				CableWest.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord, tile.yCoord, tile.zCoord + 1) != null) {
				CableEast.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord) != null) {
				CableUp.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
		try {
			if ((FunctionalEntity) world.getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord) != null) {
				CableDown.render(f5);
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
