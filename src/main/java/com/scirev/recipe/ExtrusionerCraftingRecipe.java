package com.scirev.recipe;

import java.util.ArrayList;

import com.scirev.SciRevolution;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtrusionerCraftingRecipe {
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
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.ironplate),
		        new Object[] { new ItemStack(Items.iron_ingot) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.copperplate),
		        new Object[] { new ItemStack(SciRevolution.copperingot) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.alplate),
		        new Object[] { new ItemStack(SciRevolution.alingot) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.alshell, 2),
		        new Object[] { new ItemStack(SciRevolution.alplate) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.ironshell, 2),
		        new Object[] { new ItemStack(SciRevolution.ironplate) }));
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