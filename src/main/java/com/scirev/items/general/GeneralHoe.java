package com.scirev.items.general;

import com.scirev.SciRevolution;

import net.minecraft.item.ItemHoe;
import net.minecraftforge.common.util.EnumHelper;

public class GeneralHoe extends ItemHoe {

	public GeneralHoe(String toolclass, int level, int duration, float speed, float attackDamage,
	        int enchantfortune)
    {
		super(EnumHelper.addToolMaterial(toolclass, level, duration, speed, attackDamage, enchantfortune));
		this.setCreativeTab(SciRevolution.scirevCTab);
    }

	public GeneralHoe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(SciRevolution.scirevCTab);
	}
}
