package com.yourri1012.chemistrymod.creativetabs;

import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCMItems extends CreativeTabs {

	public TabCMItems(String label) {
		super("cmitems");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.SODIUM);
	}

}
