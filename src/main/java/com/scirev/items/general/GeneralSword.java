package com.scirev.items.general;

import com.scirev.SciRevolution;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class GeneralSword extends ItemSword {

	public GeneralSword(String toolclass, int level, int duration, float speed, float attackDamage,
	        int enchantfortune)
    {
		super(EnumHelper.addToolMaterial(toolclass, level, duration, speed, attackDamage, enchantfortune));
		this.setCreativeTab(SciRevolution.scirevCTab);
    }

	public GeneralSword(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SciRevolution.scirevCTab);
	}

	public GeneralSword registerCraftingReceipe(Item material) {
		GameRegistry.addShapedRecipe(new ItemStack(this),
		        new Object[] { " # ", " # ", " * ", '#', material, '*', Items.stick });
		return this;
	}
}
