package com.scirev.blocks.container.gui;

import org.lwjgl.opengl.GL11;

import com.scirev.blocks.container.functional.container.AnvilContainer;
import com.scirev.blocks.container.functional.tileentity.AnvilEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class AnvilGUI extends GuiContainer {

	public AnvilGUI(InventoryPlayer inventory, AnvilEntity tileEntity) {
		super(new AnvilContainer(inventory, tileEntity));

		doesGuiPauseGame();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		// TODO Auto-generated method stub
		super.drawGuiContainerForegroundLayer(par1, par2);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("tile.anvil.name"), 65, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2,
		        4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("scirev", "textures/gui/GUIAnvil.png"));
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

}
