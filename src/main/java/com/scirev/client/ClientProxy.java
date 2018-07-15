package com.scirev.client;

import com.scirev.SciRevolution;
import com.scirev.blocks.container.functional.tileentity.AnvilEntity;
import com.scirev.blocks.container.functional.tileentity.BlastFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.BowlEntity;
import com.scirev.blocks.container.functional.tileentity.CableEntity;
import com.scirev.blocks.container.functional.tileentity.ElectroFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ExtrusionerTileEntity;
import com.scirev.blocks.container.functional.tileentity.ForgeMachineEntity;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;
import com.scirev.blocks.container.functional.tileentity.LatheBottomShellEntity;
import com.scirev.blocks.container.functional.tileentity.LatheManipulatePanelEntity;
import com.scirev.blocks.container.functional.tileentity.LathePowerSourceEntity;
import com.scirev.blocks.container.functional.tileentity.MaceratorTileEntity;
import com.scirev.blocks.container.functional.tileentity.SteamEngineEntity;
import com.scirev.blocks.container.functional.tileentity.WoodenPipeEntity;
import com.scirev.blocks.container.gui.AnvilGUI;
import com.scirev.blocks.container.gui.BlastFurnaceGUI;
import com.scirev.blocks.container.gui.ElectroFurnaceGUI;
import com.scirev.blocks.container.gui.ExtrusionerGUI;
import com.scirev.blocks.container.gui.ForgeMachineGUI;
import com.scirev.blocks.container.gui.GeneratorGUI;
import com.scirev.blocks.container.gui.MaceratorGUI;
import com.scirev.blocks.container.gui.SteamEngineGUI;
import com.scirev.blocks.container.gui.SteamTrainGUI;
import com.scirev.blocks.models.ModelForgeMachine;
import com.scirev.blocks.models.ModelLatheBottomShell;
import com.scirev.blocks.models.ModelLatheManipulatePanel;
import com.scirev.blocks.models.ModelLathePowerSource;
import com.scirev.blocks.models.ModelPipe;
import com.scirev.blocks.models.ModelSteamEngine;
import com.scirev.blocks.models.tileentity.RenderBowl;
import com.scirev.blocks.models.tileentity.RenderCable;
import com.scirev.blocks.models.tileentity.RenderFM;
import com.scirev.blocks.models.tileentity.RenderLBS;
import com.scirev.blocks.models.tileentity.RenderLMP;
import com.scirev.blocks.models.tileentity.RenderLPS;
import com.scirev.blocks.models.tileentity.RenderPipe;
import com.scirev.blocks.models.tileentity.RenderSE;
import com.scirev.entity.SteamTrainEntity;
import com.scirev.items.render.GenericItemRenderer;
import com.scirev.server.ServerProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {

	public void registRender() {
		ClientRegistry.bindTileEntitySpecialRenderer(CableEntity.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(LatheBottomShellEntity.class, new RenderLBS());
		ClientRegistry.bindTileEntitySpecialRenderer(LatheManipulatePanelEntity.class, new RenderLMP());
		ClientRegistry.bindTileEntitySpecialRenderer(LathePowerSourceEntity.class, new RenderLPS());
		ClientRegistry.bindTileEntitySpecialRenderer(WoodenPipeEntity.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(BowlEntity.class, new RenderBowl());
		ClientRegistry.bindTileEntitySpecialRenderer(SteamEngineEntity.class, new RenderSE());
		ClientRegistry.bindTileEntitySpecialRenderer(ForgeMachineEntity.class, new RenderFM());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.woodenpipe),
		        new GenericItemRenderer(new ResourceLocation("scirev:textures/blocks/woodenpipe.png"),
		                new ModelPipe()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lmp), new GenericItemRenderer(
		        new ResourceLocation("scirev:textures/blocks/lmp.png"), new ModelLatheManipulatePanel()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lps), new GenericItemRenderer(
		        new ResourceLocation("scirev:textures/blocks/lps.png"), new ModelLathePowerSource()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lbs), new GenericItemRenderer(
		        new ResourceLocation("scirev:textures/blocks/lbs.png"), new ModelLatheBottomShell()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.se), new GenericItemRenderer(
		        new ResourceLocation("scirev:textures/blocks/steamengine.png"), new ModelSteamEngine()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.fm), new GenericItemRenderer(
		        new ResourceLocation("scirev:textures/blocks/forgemachine.png"), new ModelForgeMachine()));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case 1:
				return new GeneratorGUI(player.inventory, (GeneratorEntity) player.worldObj.getTileEntity(x, y, z));
			case 2:
				return new MaceratorGUI(player.inventory, (MaceratorTileEntity) player.worldObj.getTileEntity(x, y, z));
			case 3:
				return new BlastFurnaceGUI(player.inventory,
				        (BlastFurnaceEntity) player.worldObj.getTileEntity(x, y, z));
			case 4:
				return new ExtrusionerGUI(player.inventory,
				        (ExtrusionerTileEntity) player.worldObj.getTileEntity(x, y, z));
			case 5:
				return new ElectroFurnaceGUI(player.inventory,
				        (ElectroFurnaceEntity) player.worldObj.getTileEntity(x, y, z));
			case 6:
				return new SteamEngineGUI(player.inventory, (SteamEngineEntity) player.worldObj.getTileEntity(x, y, z));
			case 7:
				return new ForgeMachineGUI(player.inventory,
				        (ForgeMachineEntity) player.worldObj.getTileEntity(x, y, z));
			case 8:
				return new AnvilGUI(player.inventory, (AnvilEntity) player.worldObj.getTileEntity(x, y, z));
			case 9:
				return new SteamTrainGUI(player.inventory, (SteamTrainEntity) world.getEntityByID(x));
		}
		return null;
	}
}