package com.scirev.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class AdvShapelessRecipe implements IRecipe {

	/** Is the ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	/** Is a List of ItemStack that composes the recipe. */
	public final List<ItemStack> recipeItems;

	public boolean matchDamage = false;

	public AdvShapelessRecipe(ItemStack result, Object[] args) {
		// TODO Auto-generated constructor stub
		ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
		Object[] aobject = args;
		int i = args.length;

		for (int j = 0; j < i; ++j) {
			Object object1 = aobject[j];

			if (object1 instanceof ItemStack) {
				arraylist.add(((ItemStack) object1).copy());
			} else if (object1 instanceof Item) {
				arraylist.add(new ItemStack((Item) object1));
			} else {
				if (!(object1 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				arraylist.add(new ItemStack((Block) object1));
			}
		}

		this.recipeOutput = result;
		this.recipeItems = arraylist;
	}

	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_) {
		// TODO Auto-generated method stub
		ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>(this.recipeItems);

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				ItemStack itemstack = p_77569_1_.getStackInRowAndColumn(j, i);

				if (itemstack != null) {
					boolean flag = false;
					Iterator<ItemStack> iterator = arraylist.iterator();

					while (iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack) iterator.next();

						if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767
						        || itemstack.getItemDamage() == itemstack1.getItemDamage()) || !matchDamage) {
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag) {
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return this.recipeOutput.copy();
	}

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return this.recipeItems.size();
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return this.recipeOutput;
	}

}
