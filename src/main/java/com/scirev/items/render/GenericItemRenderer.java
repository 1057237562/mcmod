package com.scirev.items.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class GenericItemRenderer implements IItemRenderer {

	private ModelBase model;
	private ResourceLocation resourceLocation;

	public GenericItemRenderer(ResourceLocation resourceLocation, ModelBase model) {
		this.resourceLocation = resourceLocation;
		this.model = model;
	}


	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub
		GL11.glPushMatrix();
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glScaled(1.4f, 1.4f, 1.4f);
			GL11.glTranslated(0, 1.8, 0.5);
			GL11.glRotated(180, 1f, 0, 0);
		} else {
			GL11.glTranslated(0f, 1.5, 0.5);
			GL11.glRotated(180, 1f, 0, 0);
		}

		//GL11.glRotated(90f, 0, 1, 0);
		//GL11.glTranslatef(0.125f, 1.5f, -3.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
		model.render(null, 0, 0, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}

}
