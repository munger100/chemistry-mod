package com.yourri1012.chemistrymod.items.potions;

import com.yourri1012.chemistrymod.Main;
import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.init.PotionInit;
import com.yourri1012.chemistrymod.util.IHasModel;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class CustomPotion extends Potion {
	public CustomPotion(String name, boolean isBadPotion, int colour, int iconIndexX, int iconIndexY) {
		super(isBadPotion, colour);
		setPotionName("effect." + name);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + name));
	}
	
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/inventory.png"));
		return true;
	}
	
	


}
