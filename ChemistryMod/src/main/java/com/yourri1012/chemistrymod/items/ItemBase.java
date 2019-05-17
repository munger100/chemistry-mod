package com.yourri1012.chemistrymod.items;

import com.yourri1012.chemistrymod.Main;
import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.chemistrytab);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}
