package com.yourri1012.chemistrymod.tabs;

import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ChemistryTab extends CreativeTabs {
	public ChemistryTab(String label) {
		super("chemistrytab");
		this.setBackgroundImageName("chemistry.png");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.OBJECTIVE_SKIP);
	}
	
}
