package com.yourri1012.chemistrymod.items.blocks.slots;

import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ObjectiveTableSlots extends SlotItemHandler{

	public ObjectiveTableSlots(IItemHandler inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		for (ItemStack valid : this.valid_items) {
			if (valid.isItemEqualIgnoreDurability(stack)) {
				return true;
			}
		} return false;
	}
	
	public ItemStack[] valid_items = {
			new ItemStack(ModItems.ALLOY),
			new ItemStack(ModItems.MOLTEN_COPPER),
			new ItemStack(ModItems.MOLTEN_DIAMOND),
			new ItemStack(ModItems.MOLTEN_EMERALD),
			new ItemStack(ModItems.MOLTEN_GOLD),
			new ItemStack(ModItems.MOLTEN_IRON),
			new ItemStack(ModItems.MOLTEN_PLATINUM),
			new ItemStack(ModItems.OBJECTIVE_SKIP),
			new ItemStack(ModItems.SALT),
			new ItemStack(ModItems.SALTY_WATER),
			new ItemStack(ModItems.STRONG_ACID),
			new ItemStack(ModItems.STRONG_BASE),
			new ItemStack(ModItems.WEAK_ACID),
			new ItemStack(ModItems.WEAK_BASE)
	};
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}
	

}

