package com.scirev.client;

import com.scirev.blocks.container.functional.tileentity.BlastFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ElectroFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ExtrusionerTileEntity;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;
import com.scirev.blocks.container.functional.tileentity.MaceratorTileEntity;
import com.scirev.blocks.container.functional.tileentity.SteamEngineEntity;
import com.scirev.blocks.container.gui.BlastFurnaceGUI;
import com.scirev.blocks.container.gui.ElectroFurnaceGUI;
import com.scirev.blocks.container.gui.ExtrusionerGUI;
import com.scirev.blocks.container.gui.GeneratorGUI;
import com.scirev.blocks.container.gui.MaceratorGUI;
import com.scirev.blocks.container.gui.SteamEngineGUI;
import com.scirev.server.ServerProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends ServerProxy {
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
		}
		return null;
	}
}