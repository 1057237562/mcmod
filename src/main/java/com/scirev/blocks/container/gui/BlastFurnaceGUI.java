package com.scirev.blocks.container.gui;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.container.functional.container.BlastFurnaceContainer;
import com.scirev.blocks.container.functional.tileentity.BlastFurnaceEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class BlastFurnaceGUI extends GuiContainer {

	BlastFurnaceEntity tile;

	public BlastFurnaceGUI(InventoryPlayer inventory, BlastFurnaceEntity entity) {
		super(new BlastFurnaceContainer(inventory, entity));
		tile = entity;
		doesGuiPauseGame();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("tile.BlastFurnace.name"), 65, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2,
		        4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("scirev", "textures/gui/GUIBlastFurnace.png"));
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int b = tile.BurnTime;
		float maxBurnTime = tile.maxBurnTime * 1.0F;
		int progress = tile.progress;
		if (b > 0 && maxBurnTime > 0) {
			this.drawTexturedModalRect(this.guiLeft + 57,
			        this.guiTop + 37 + (int) (14 - 14 * ((float) b / maxBurnTime)), 176,
			        (int) (14 - 14 * ((float) b / maxBurnTime)), 14, (int) (14 * ((float) b / maxBurnTime)));
			this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, (int) ((float) progress * 24 / 200), 16);
		}
	}

}
