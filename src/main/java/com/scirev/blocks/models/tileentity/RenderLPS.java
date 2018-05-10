package com.scirev.blocks.models.tileentity;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.models.ModelLathePowerSource;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderLPS extends TileEntitySpecialRenderer {
	private ModelLathePowerSource model;
	private ResourceLocation resourceLocation = new ResourceLocation("scirev:textures/blocks/lathepowersource.png");

	public RenderLPS() {
		model = new ModelLathePowerSource();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		// TODO Auto-generated method stub

		//resourceLocation = new ResourceLocation(
		//"scirev:textures/blocks/" + CableEntity.material[tile.getBlockMetadata()] + "cable.png");

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotated(180, 1f, 0, 0);
		GL11.glRotated(tile.getBlockMetadata() * 90f, 0, 1, 0);
		bindTexture(resourceLocation);
		model.render(null, 0, 0, -0.1f, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}
}