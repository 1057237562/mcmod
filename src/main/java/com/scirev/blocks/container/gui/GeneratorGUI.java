package com.scirev.blocks.container.gui;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.container.functional.container.GeneratorContainer;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GeneratorGUI extends GuiContainer {

	GeneratorEntity tile;

	public GeneratorGUI(InventoryPlayer inventory, GeneratorEntity entity) {
		super(new GeneratorContainer(inventory, entity));
		tile = entity;
		doesGuiPauseGame();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("tile.Generator.name"), 65, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2,
		        4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("scirev", "textures/gui/GUIGenerator.png"));
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int b = tile.generatorBurnTime;
		float maxBurnTime = tile.maxBurnTime * 1.0F;
		if (b > 0 && maxBurnTime > 0) {
			this.drawTexturedModalRect(this.guiLeft + 66,
			        this.guiTop + 20 + (int) (14 - 14 * ((float) b / maxBurnTime)), 176,
			        (int) (14 - 14 * ((float) b / maxBurnTime)), 14, (int) (14 * ((float) b / maxBurnTime)));
		}

		int p = tile.power;
		float mp = tile.maxpower * 1f;
		if (p > 0) {
			this.drawTexturedModalRect(this.guiLeft + 94, this.guiTop + 35, 176, 14, (int) (24 * ((float) p / mp)), 17);
		}
	}

}
