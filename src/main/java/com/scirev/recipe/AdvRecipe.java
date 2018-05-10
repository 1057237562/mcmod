package com.scirev.recipe;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AdvRecipe implements IRecipe {

	/** How many horizontal slots this recipe is wide. */
	public final int recipeWidth;
	/** How many vertical slots this recipe uses. */
	public final int recipeHeight;
	/** Is a array of ItemStack that composes the recipe. */
	public final ItemStack[] recipeItems;
	/** Is the ItemStack that you get when craft the recipe. */
	private ItemStack recipeOutput;

	public boolean matchDamage = false;

	public AdvRecipe(ItemStack result, Object... args) {
		// TODO Auto-generated constructor stub

		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;

		if (args[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) args[i++]);

			for (int l = 0; l < astring.length; ++l) {
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		} else {
			while (args[i] instanceof String) {
				String s2 = (String) args[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}

		HashMap<Character, ItemStack> hashmap;

		for (hashmap = new HashMap<Character, ItemStack>(); i < args.length; i += 2) {
			Character character = (Character) args[i];
			ItemStack itemstack1 = null;

			if (args[i + 1] instanceof Item) {
				itemstack1 = new ItemStack((Item) args[i + 1]);
			} else if (args[i + 1] instanceof Block) {
				itemstack1 = new ItemStack((Block) args[i + 1], 1, 32767);
			} else if (args[i + 1] instanceof ItemStack) {
				itemstack1 = (ItemStack) args[i + 1];
			}

			hashmap.put(character, itemstack1);
		}

		ItemStack[] aitemstack = new ItemStack[j * k];

		for (int i1 = 0; i1 < j * k; ++i1) {
			char c0 = s.charAt(i1);

			if (hashmap.containsKey(Character.valueOf(c0))) {
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			} else {
				aitemstack[i1] = null;
			}
		}

		recipeWidth = j;
		recipeHeight = k;
		recipeItems = aitemstack;
		recipeOutput = result;
	}

	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - this.recipeHeight; ++j) {
				if (this.checkMatch(p_77569_1_, i, j, true)) {
					return true;
				}

				if (this.checkMatch(p_77569_1_, i, j, false)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg1) {
		// TODO Auto-generated method stub
		ItemStack itemstack = this.getRecipeOutput().copy();
		for (int i = 0; i < arg1.getSizeInventory(); ++i) {
			ItemStack itemstack1 = arg1.getStackInSlot(i);

			if (itemstack1 != null && itemstack1.hasTagCompound()) {
				itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
			}
		}

		return itemstack;
	}

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return this.recipeWidth * this.recipeHeight;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return recipeOutput;
	}

	private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		for (int k = 0; k < 3; ++k) {
			for (int l = 0; l < 3; ++l) {
				int i1 = k - p_77573_2_;
				int j1 = l - p_77573_3_;
				ItemStack itemstack = null;

				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
					if (p_77573_4_) {
						itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
					} else {
						itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}

				ItemStack itemstack1 = p_77573_1_.getStackInRowAndColumn(k, l);

				if (itemstack1 != null || itemstack != null) {
					if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
						return false;
					}

					if (itemstack.getItem() != itemstack1.getItem()) {
						return false;
					}

					if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()
					        && matchDamage) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
