package com.scirev.server;

import com.scirev.blocks.container.functional.container.AnvilContainer;
import com.scirev.blocks.container.functional.container.BlastFurnaceContainer;
import com.scirev.blocks.container.functional.container.ElectroFurnaceContainer;
import com.scirev.blocks.container.functional.container.ExtrusionerContainer;
import com.scirev.blocks.container.functional.container.ForgeMachineContainer;
import com.scirev.blocks.container.functional.container.GeneratorContainer;
import com.scirev.blocks.container.functional.container.MaceratorContainer;
import com.scirev.blocks.container.functional.container.SteamEngineContainer;
import com.scirev.blocks.container.functional.container.SteamTrainContainer;
import com.scirev.blocks.container.functional.tileentity.AnvilEntity;
import com.scirev.blocks.container.functional.tileentity.BlastFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ElectroFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ExtrusionerTileEntity;
import com.scirev.blocks.container.functional.tileentity.ForgeMachineEntity;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;
import com.scirev.blocks.container.functional.tileentity.MaceratorTileEntity;
import com.scirev.blocks.container.functional.tileentity.SteamEngineEntity;
import com.scirev.entity.SteamTrainEntity;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		switch (ID) {
			case 1:
				return new GeneratorContainer(player.inventory,
				        (GeneratorEntity) player.worldObj.getTileEntity(x, y, z));
			case 2:
				return new MaceratorContainer(player.inventory,
				        (MaceratorTileEntity) player.worldObj.getTileEntity(x, y, z));
			case 3:
				return new BlastFurnaceContainer(player.inventory,
				        (BlastFurnaceEntity) player.worldObj.getTileEntity(x, y, z));
			case 4:
				return new ExtrusionerContainer(player.inventory,
				        (ExtrusionerTileEntity) player.worldObj.getTileEntity(x, y, z));
			case 5:
				return new ElectroFurnaceContainer(player.inventory,
				        (ElectroFurnaceEntity) player.worldObj.getTileEntity(x, y, z));
			case 6:
				return new SteamEngineContainer(player.inventory,
				        (SteamEngineEntity) player.worldObj.getTileEntity(x, y, z));
			case 7:
				return new ForgeMachineContainer(player.inventory,
				        (ForgeMachineEntity) player.worldObj.getTileEntity(x, y, z));
			case 8:
				return new AnvilContainer(player.inventory, (AnvilEntity) player.worldObj.getTileEntity(x, y, z));
			case 9:
				return new SteamTrainContainer(player.inventory, (SteamTrainEntity) world.getEntityByID(x));
		}
		return null;
	}
}