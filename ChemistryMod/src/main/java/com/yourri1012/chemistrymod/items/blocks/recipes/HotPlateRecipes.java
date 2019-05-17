package com.yourri1012.chemistrymod.items.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;



public class HotPlateRecipes{	

	private static final HotPlateRecipes INSTANCE = new HotPlateRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> hotPlateList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static HotPlateRecipes getInstance()	{
		return INSTANCE;
	}

	private HotPlateRecipes() {
		addHotPlateRecipe(new ItemStack(ModItems.COPPER), ItemStack.EMPTY, new ItemStack(ModItems.MOLTEN_COPPER), 0.0F);
		addHotPlateRecipe(new ItemStack(Items.GOLD_INGOT), ItemStack.EMPTY, new ItemStack(ModItems.MOLTEN_GOLD), 0.0F);
		addHotPlateRecipe(new ItemStack(Items.IRON_INGOT), ItemStack.EMPTY, new ItemStack(ModItems.MOLTEN_IRON), 0.0F);
		addHotPlateRecipe(new ItemStack(ModItems.PLATINUM), ItemStack.EMPTY, new ItemStack(ModItems.MOLTEN_PLATINUM), 0.0F);
	}

	public void addHotPlateRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getHotPlateResult(input1) != ItemStack.EMPTY) return;
		this.hotPlateList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getHotPlateResult(ItemStack input1) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.hotPlateList.columnMap().entrySet()) {
			if (this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					return (ItemStack)ent.getValue();
				}
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	

	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.hotPlateList;
	}

	public float getSinteringExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}