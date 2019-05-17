package com.yourri1012.chemistrymod.init;


import com.yourri1012.chemistrymod.items.potions.CustomPotion;
import com.yourri1012.chemistrymod.world.WorldEvents;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {
	public static final Potion FASTER_POTION_EFFECT = new CustomPotion("faster_potion", false, 13369343, 1, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), 2.0D, 1);
	public static final PotionType FASTER_POTION = new PotionType("faster_potion", new PotionEffect[] {new PotionEffect(FASTER_POTION_EFFECT, 600)}).setRegistryName("faster_potion");
	public static final PotionType LONG_FASTER_POTION = new PotionType("faster_potion", new PotionEffect[] {new PotionEffect(FASTER_POTION_EFFECT, 1200)}).setRegistryName("long_faster_potion");
	
	public static final Potion ANTI_GRAVITY_EFFECT = new CustomPotion("anti_gravity", false, 13369343, 2, 0); 
	public static final PotionType ANTI_GRAVITY= new PotionType("anti_gravity", new PotionEffect[] {new PotionEffect(ANTI_GRAVITY_EFFECT, 200)}).setRegistryName("anti_gravity");
	public static final PotionType LONG_ANTI_GRAVITY= new PotionType("anti_gravity", new PotionEffect[] {new PotionEffect(ANTI_GRAVITY_EFFECT, 400)}).setRegistryName("long_anti_gravity");
	
	public static final Potion FIRE_POTION_EFFECT = new CustomPotion("fire_potion", false, 16758016, 0, 0);
	public static final PotionType FIRE_POTION = new PotionType("fire_potion", new PotionEffect[] {new PotionEffect(FIRE_POTION_EFFECT, 160)}).setRegistryName("fire_vision");
	public static final PotionType LONG_FIRE_EFFECT = new PotionType("fire_potion", new PotionEffect[] {new PotionEffect(FIRE_POTION_EFFECT, 320)}).setRegistryName("long_fire");
	
	public static void registerPotions() {
		registerPotion(FASTER_POTION, LONG_FASTER_POTION, FASTER_POTION_EFFECT);
		registerPotion(FIRE_POTION, LONG_FIRE_EFFECT, FIRE_POTION_EFFECT);
		registerPotion(ANTI_GRAVITY, LONG_ANTI_GRAVITY, ANTI_GRAVITY_EFFECT );
		registerPotionMixes();
	}
	
	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
	}
	
	private static void registerPotionMixes() {
		PotionHelper.addMix(FASTER_POTION, Items.REDSTONE, LONG_FASTER_POTION);
		PotionHelper.addMix(PotionTypes.AWKWARD,  ModItems.CALCIUM, LONG_FASTER_POTION);
		
		PotionHelper.addMix(FIRE_POTION, Items.REDSTONE, LONG_FIRE_EFFECT);
		PotionHelper.addMix(PotionTypes.AWKWARD,  ModItems.CALCIUM, LONG_FIRE_EFFECT);
		
		PotionHelper.addMix(ANTI_GRAVITY, Items.REDSTONE, LONG_ANTI_GRAVITY);
		PotionHelper.addMix(PotionTypes.AWKWARD,  ModItems.CALCIUM, LONG_ANTI_GRAVITY);
	}
}
