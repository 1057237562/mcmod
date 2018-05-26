package com.scirev.handler;

import com.scirev.SciRevolution;
import com.scirev.entity.SteamTrainEntity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class EventHandlers {
	@SubscribeEvent
	public void onPlayerEntityInteract(EntityInteractEvent evt) {
		if (evt.target instanceof SteamTrainEntity) {
			if (evt.entityPlayer.getHeldItem() == null ? false
			        : evt.entityPlayer.getHeldItem().getItem() == Items.water_bucket) {
				evt.entityPlayer.inventory.setInventorySlotContents(evt.entityPlayer.inventory.currentItem,
				        new ItemStack(Items.bucket));
				((SteamTrainEntity) evt.target).waterstorage = 1000;
			} else {
				FMLNetworkHandler.openGui(evt.entityPlayer, SciRevolution.instance, 9, evt.entityPlayer.worldObj,
				        evt.target.getEntityId(), 0, 0);
			}
		}
	}
}
