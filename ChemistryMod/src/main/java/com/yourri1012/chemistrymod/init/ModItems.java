package com.yourri1012.chemistrymod.init;

import java.util.ArrayList;
import java.util.List;

import com.yourri1012.chemistrymod.items.ItemBase;
import com.yourri1012.chemistrymod.items.armor.ArmorBase;
import com.yourri1012.chemistrymod.items.potions.CustomPotion;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems {
	public static final List<Item>  ITEMS = new ArrayList<Item>();
	
	
	// Custom Items
	public static final Item WEAK_ACID = new ItemBase("weak_acid");
	public static final Item STRONG_ACID = new ItemBase("strong_acid");
	public static final Item WEAK_BASE = new ItemBase ("weak_base");
	public static final Item STRONG_BASE = new ItemBase ("strong_base");
	public static final Item SALT = new  ItemBase("salt");
	public static final Item HELIUM = new ItemBase("helium");
	public static final Item HYDROGEN = new ItemBase("hydrogen");
	public static final Item OBJECTIVE_SKIP = new ItemBase("objective_skip");
	public static final Item CHLORINE = new ItemBase("chlorine");
	public static final Item MOLTEN_IRON = new ItemBase("molten_iron");
	public static final Item ALLOY = new ItemBase("alloy");
	public static final Item MOLTEN_GOLD = new ItemBase("molten_gold");
	public static final Item MOLTEN_DIAMOND = new ItemBase("molten_diamond");
	public static final Item MOLTEN_EMERALD = new ItemBase("molten_emerald");
	public static final Item SALTY_WATER = new ItemBase("salty_water");
	
	// Melt-able Items
	public static final Item CALCIUM = new ItemBase("calcium");
	public static final Item SODIUM = new ItemBase("sodium");
	public static final Item COPPER = new ItemBase("copper");
	public static final Item MOLTEN_COPPER = new ItemBase("molten_copper");
	public static final Item PLATINUM = new ItemBase("platinum");
	public static final Item MOLTEN_PLATINUM = new ItemBase("molten_platinum");
	
	//Materials
	public static final ArmorMaterial ARMOR_MATERIAL_LAB = EnumHelper.addArmorMaterial("armor_material_lab", Reference.MOD_ID + ":lab", 15, 
			new int[] {20, 20, 20, 20}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
	//Armor
	public static final Item LAB_GOGGLES = new ArmorBase("lab_goggles", ARMOR_MATERIAL_LAB, 1, EntityEquipmentSlot.HEAD);
	public static final Item LAB_COAT = new ArmorBase("lab_coat", ARMOR_MATERIAL_LAB, 1, EntityEquipmentSlot.CHEST);

}
	
