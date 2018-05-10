package com.scirev.recipe;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class AdvCraftingManager {

	@SuppressWarnings("unchecked")
	public static void addAndRegister(IRecipe recipe) {
		try {
			CraftingManager.getInstance().getRecipeList().add(recipe);
		} catch (RuntimeException e) {

		}
	}
}
