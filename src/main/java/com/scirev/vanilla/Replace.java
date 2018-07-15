package com.scirev.vanilla;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;

public class Replace {



	public static boolean replaceBlock(Block block, Block newBlock, ItemBlock newItemBlock, boolean replaceField) {
		try {
			FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
			String registryName = registry.getNameForObject(block);
			int id = Block.getIdFromBlock(block);
			ItemBlock itemBlock = (ItemBlock) Item.getItemFromBlock(block);

			// Replace registry entry
			Reflect.invoke(ForgeConstants.METHOD_FMLControlledNamespacedRegistry_addObjectRaw, registry,
			        new Object[] { id, registryName, newBlock });

			// Replace delegate referant
			Reflect.setField(ForgeConstants.FIELD_Delegate_referant, block.delegate, newBlock);

			// Replace ItemBlock
			if (newItemBlock != null) {
				replaceItem(itemBlock, newItemBlock, false);

				// BUGFIX: Replace mineBlockStat to avoid statistics crash.
				StatCrafting stat = (StatCrafting) StatList.mineBlockStatArray[id];
				if (stat != null) {
					Reflect.setField(ForgeConstants.FIELD_StatCrafting_item, stat, newItemBlock);
				}
			}
			// Use old ItemBlock and update reference
			else if (itemBlock != null) {
				Reflect.setField(ForgeConstants.FIELD_ItemBlock_block, itemBlock, newBlock);
			}

			// Replace Field
			if (replaceField) {
				for (Field field : Blocks.class.getDeclaredFields()) {
					if (!Block.class.isAssignableFrom(field.getType())) {
						continue;
					}

					Block block1 = (Block) field.get(null);
					if (block1 != block) {
						continue;
					}

					// Set field
					Reflect.setModifier(field, Modifier.FINAL, false);
					field.set(null, newBlock);
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean replaceItem(Item item, Item bowl, boolean replaceField) {
		try {
			FMLControlledNamespacedRegistry<Item> registry = GameData.getItemRegistry();
			String registryName = registry.getNameForObject(item);
			int id = Item.getIdFromItem(item);

			// Replace registry entry
			Reflect.invoke(ForgeConstants.METHOD_FMLControlledNamespacedRegistry_addObjectRaw, registry,
			        new Object[] { id, registryName, bowl }); // Use Old Method to regist block in 1.6 Kinda

			// Replace delegate referant
			Reflect.setField(ForgeConstants.FIELD_Delegate_referant, item.delegate, bowl);

			// Replace stat list entries
			StatCrafting stat = (StatCrafting) StatList.objectBreakStats[id];
			if (stat != null) {
				Reflect.setField(ForgeConstants.FIELD_StatCrafting_item, stat, bowl);
			}
			stat = (StatCrafting) StatList.objectCraftStats[id];
			if (stat != null) {
				Reflect.setField(ForgeConstants.FIELD_StatCrafting_item, stat, bowl);
			}
			stat = (StatCrafting) StatList.objectUseStats[id];
			if (stat != null) {
				Reflect.setField(ForgeConstants.FIELD_StatCrafting_item, stat, bowl);
			}

			// Replace Crafting Recipes
			//replacements.put(item, newItem);

			// Replace Field
			if (replaceField) {
				for (Field field : Items.class.getDeclaredFields()) {
					if (!Item.class.isAssignableFrom(field.getType())) {
						continue;
					}

					Item item1 = (Item) field.get(null);
					if (item1 != item) {
						continue;
					}

					// Set field
					Reflect.setModifier(field, Modifier.FINAL, false);
					field.set(null, bowl);
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
