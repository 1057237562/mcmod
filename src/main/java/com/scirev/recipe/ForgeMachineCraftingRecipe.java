package com.scirev.recipe;

import java.util.ArrayList;

import com.scirev.SciRevolution;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForgeMachineCraftingRecipe {
	static ArrayList<AdvShapelessRecipe> arrayList = new ArrayList<AdvShapelessRecipe>();

	public static void addRecipe(AdvShapelessRecipe recipe) {
		arrayList.add(recipe);
	}

	public static boolean isRecipeItem(Item item) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(0).getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	public static void registerRecipe() {
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.steel_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.meltiron, 1) }));
	}

	public static ItemStack getOutput(ItemStack item) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(0).getItem().equals(item.getItem())) {
				if (recipe.recipeItems.get(0).stackSize <= item.stackSize) {
					return recipe.getRecipeOutput();
				}
			}
		}
		return null;
	}

	public static ItemStack getInputStack(ItemStack item) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(0).getItem().equals(item.getItem())) {
				if (recipe.recipeItems.get(0).stackSize <= item.stackSize) {
					return recipe.recipeItems.get(0);
				}
			}
		}
		return null;
	}
}