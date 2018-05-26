package com.scirev.recipe;

import java.util.ArrayList;

import com.scirev.SciRevolution;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlastFurnaceRecipe {
	static ArrayList<AdvShapelessRecipe> arrayList = new ArrayList<AdvShapelessRecipe>();

	public static void addRecipe(AdvShapelessRecipe recipe) {
		arrayList.add(recipe);
	}

	public static boolean isRecipeItem(Item item, int stack) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(stack).getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	public static void registerRecipe() {
		addRecipe(new AdvShapelessRecipe(new ItemStack(Items.iron_ingot, 1),
		        new Object[] { new ItemStack(Blocks.iron_ore, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(Items.iron_ingot, 1),
		        new Object[] { new ItemStack(Blocks.iron_ore, 1), new ItemStack(Items.coal, 1) }));

		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.alingot, 2),
		        new Object[] { new ItemStack(SciRevolution.ore_alblock, 2), new ItemStack(Items.coal, 3) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.alingot, 2),
		        new Object[] { new ItemStack(SciRevolution.alpowder, 2), new ItemStack(Items.coal, 3) }));

		addRecipe(new AdvShapelessRecipe(new ItemStack(Items.iron_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ironpowder, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(Items.iron_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ironpowder, 1), new ItemStack(Items.coal, 1) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.tin_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ore_tinblock, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.tin_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ore_tinblock, 1), new ItemStack(Items.coal, 1) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.tin_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.tinpowder, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.tin_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.tinpowder, 1), new ItemStack(Items.coal, 1) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.zinc_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ore_zincblock, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.zinc_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.ore_zincblock, 1), new ItemStack(Items.coal, 1) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.zinc_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.zincpowder, 1), new ItemStack(SciRevolution.co, 2) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.zinc_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.zincpowder, 1), new ItemStack(Items.coal, 1) }));

		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.brass_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.copperingot, 1),
		                new ItemStack(SciRevolution.zinc_ingot, 1) }));
		addRecipe(new AdvShapelessRecipe(new ItemStack(SciRevolution.bronze_ingot, 1),
		        new Object[] { new ItemStack(SciRevolution.copperingot, 1),
		                new ItemStack(SciRevolution.tin_ingot, 1) }));
	}

	public static ItemStack getOutput(ItemStack item, ItemStack item2) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(0).getItem().equals(item.getItem())
			        && recipe.recipeItems.get(1).getItem().equals(item2.getItem())) {
				if (recipe.recipeItems.get(0).stackSize <= item.stackSize
				        && recipe.recipeItems.get(1).stackSize <= item2.stackSize) {
					return recipe.getRecipeOutput();
				}
			}
		}
		return null;
	}

	public static ItemStack getInputStack(ItemStack item, int stack) {
		for (AdvShapelessRecipe recipe : arrayList.toArray(new AdvShapelessRecipe[0])) {
			if (recipe.recipeItems.get(stack).getItem().equals(item.getItem())) {
				if (recipe.recipeItems.get(stack).stackSize <= item.stackSize) {
					return recipe.recipeItems.get(stack);
				}
			}
		}
		return null;
	}
}
