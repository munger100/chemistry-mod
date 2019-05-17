package com.yourri1012.chemistrymod.creativetabs;

import com.yourri1012.chemistrymod.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabCMBlocks extends CreativeTabs {

	public TabCMBlocks(String label) {
		super("cmblocks");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.ALCHEMY_TABLE);
	}

}
