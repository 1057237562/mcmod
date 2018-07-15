package com.scirev.vanilla;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.RegistryDelegate.Delegate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.stats.StatCrafting;

public class ForgeConstants {
	public static final Method METHOD_FMLControlledNamespacedRegistry_addObjectRaw = Reflect.getMethod(
	        FMLControlledNamespacedRegistry.class, "addObjectRaw",
	        new Class[] { int.class, String.class, Object.class });
	public static final Field FIELD_ItemBlock_block = Reflect.getField(ItemBlock.class, 0);
	public static final Field FIELD_StatCrafting_item = Reflect.getField(StatCrafting.class, 0);
	public static final Field FIELD_ItemArmor_armorType = Reflect.getField(ItemArmor.class, 4);
	public static final Field FIELD_ItemArmor_damageReduction = Reflect.getField(ItemArmor.class, 5);
	public static final Field FIELD_EntityList_classToIDMapping = Reflect.getField(EntityList.class, 4);
	public static final Field FIELD_EntityList_stringToIDMapping = Reflect.getField(EntityList.class, 5);
	public static final Field FIELD_CreativeTabs_tabIndex = Reflect.getField(CreativeTabs.class, 13);
	public static final Field FIELD_Delegate_referant = Reflect.getField(Delegate.class, 0);
}
