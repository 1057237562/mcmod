package com.scirev.handler;

import java.util.HashSet;

import com.google.common.collect.Sets;
import com.scirev.SciRevolution;
import com.scirev.entity.SteamTrainEntity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

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

	private static final HashSet<Block> axe_class = Sets
	        .newHashSet(new Block[] { Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest });
	private static final HashSet<Block> pickaxe_class = Sets.newHashSet(new Block[] { Blocks.cobblestone,
	        Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone,
	        Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
	        Blocks.diamond_block, Blocks.lapis_ore, Blocks.lapis_block,
	        Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail,
	        Blocks.activator_rail });
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed e) {
		EntityPlayer player = e.entityPlayer;
		ItemStack itemStack = player.getCurrentEquippedItem();
		Block block = e.block;
		if (block == null) {
			return;
		}
		if (axe_class.contains(block) && !(itemStack != null && itemStack.getItem() instanceof ItemAxe)) {
			e.setCanceled(true);
		}
		if (pickaxe_class.contains(block) && !(itemStack != null && itemStack.getItem() instanceof ItemPickaxe)) {
			e.setCanceled(true);
		}
	}
}
