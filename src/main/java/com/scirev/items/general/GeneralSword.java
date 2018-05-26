package com.scirev.items.general;

import com.scirev.SciRevolution;

import net.minecraft.item.ItemHoe;
import net.minecraftforge.common.util.EnumHelper;

public class GeneralSword extends ItemHoe {

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
}
