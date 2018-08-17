package com.scirev.recipe;

import com.scirev.SciRevolution;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VanillaRecipe {
	public static void registRecipe() {
		registRecipe(Items.iron_pickaxe, Items.iron_axe, Items.iron_sword, Items.iron_hoe, Items.iron_shovel,
		        SciRevolution.ironplate);
	}

	public static void registRecipe(Item pickaxe, Item axe, Item sword, Item hoe, Item shovel,
	        Item material) {
		GameRegistry.addShapedRecipe(new ItemStack(axe),
		        new Object[] { " ##", " *#", " * ", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(axe),
		        new Object[] { "## ", "#* ", " * ", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(hoe),
		        new Object[] { " ##", " * ", " * ", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(hoe),
		        new Object[] { "## ", " * ", " * ", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(pickaxe),
		        new Object[] { "###", " * ", " * ", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(shovel),
		        new Object[] { "#", "*", "*", '#', material, '*', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(sword),
		        new Object[] { "#", "#", "*", '#', material, '*', Items.stick });
	}
}
