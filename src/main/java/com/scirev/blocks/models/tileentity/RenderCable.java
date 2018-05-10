package com.scirev.blocks.models.tileentity;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.container.functional.tileentity.CableEntity;
import com.scirev.blocks.models.ModelCable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCable extends TileEntitySpecialRenderer {

	private ModelCable model;
	private ResourceLocation resourceLocation = new ResourceLocation("scirev:textures/blocks/cable.png");

	public RenderCable() {
		model = new ModelCable();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		// TODO Auto-generated method stub

		resourceLocation = new ResourceLocation(
		        "scirev:textures/blocks/" + CableEntity.material[tile.getBlockMetadata()] + "cable.png");

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y - 0.5, z + 0.5);
		bindTexture(resourceLocation);
		model.render(null, 0, 0, -0.1f, 0, 0, 0.0625f, tile);
		GL11.glPopMatrix();
	}

}
