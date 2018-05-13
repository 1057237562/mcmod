package com.scirev.blocks.models.tileentity;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.container.functional.tileentity.WoodenPipeEntity;
import com.scirev.blocks.models.ModelPipe;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderPipe extends TileEntitySpecialRenderer {

	private ModelPipe model;
	private ResourceLocation resourceLocation = new ResourceLocation("scirev:textures/blocks/woodenpipe.png");

	public RenderPipe() {
		model = new ModelPipe();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		// TODO Auto-generated method stub

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotated(180, 1f, 0, 0);
		GL11.glRotated(tile.getBlockMetadata() * 90f, 0, 1, 0);
		bindTexture(resourceLocation);
		model.render(null, 0, 0, -0.1f, 0, 0, 0.0625f, ((WoodenPipeEntity) tile).isFlowing());
		GL11.glPopMatrix();
	}

}