package com.yourri1012.chemistrymod.items.blocks.slots;

import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHotPlateOutput extends Slot {
	private final EntityPlayer player;
	private int removeCount;
	
	public SlotHotPlateOutput(EntityPlayer player, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.player = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		for (ItemStack valid : this.valid_items) {
			if (valid.isItemEqualIgnoreDurability(stack)) {
				return true;
			}
		} return false;
	}
	/*(new ItemStack(ModItems.COPPER), new ItemStack(ModItems.MOLTEN_COPPER), 5.0F);
	addHotPlateRecipe(new ItemStack(ModItems.CALCIUM), new ItemStack(ModItems.MOLTEN_CALCIUM), 5.0F);
	addHotPlateRecipe(new ItemStack(ModItems.PLATINUM), new ItemStack(ModItems.MOLTEN_PLATINUM), 5.0F);
	addHotPlateRecipe(new ItemStack(ModItems.SODIUM), new ItemStack(ModItems.MOLTEN_SODIUM),*/
	public ItemStack[] valid_items = {
			new ItemStack(ModItems.MOLTEN_COPPER),
			new ItemStack(ModItems.MOLTEN_PLATINUM),
	};
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		// TODO Auto-generated method stub
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	public void onCrafting(ItemStack stack) {
		 
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		if (this.getHasStack()) {
			this.removeCount += Math.min(amount, this.getStack().getCount());
		}
		return super.decrStackSize(amount);
	}

}
