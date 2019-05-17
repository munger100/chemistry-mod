package com.yourri1012.chemistrymod.items.blocks.containers;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Ordering;
import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.items.blocks.slots.SlotHotPlateOutput;
import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerHotPlate extends Container {
	private final TileEntityHotPlate tileEntity;
	private int cookTime,  totalCookTime;
	public ItemStack[] valid_inputs = {
			new ItemStack(ModItems.CALCIUM),
			new ItemStack(ModItems.COPPER),
			new ItemStack(ModItems.PLATINUM),
			new ItemStack(ModItems.SODIUM),
			new ItemStack(Items.GOLD_INGOT),
			new ItemStack(Items.IRON_INGOT),
			new ItemStack(Items.EMERALD),
			new ItemStack(Items.DIAMOND)
	};
	
	public ContainerHotPlate(InventoryPlayer player, TileEntityHotPlate tileEntity) {
		this.tileEntity = tileEntity;
		IItemHandler inventory = this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		this.addSlotToContainer(new SlotItemHandler(inventory, 0, 48, 35) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				for (ItemStack valid : valid_inputs) {
					if (valid.isItemEqualIgnoreDurability(stack)) {
						return true;
					}
				} return false;
			}
			
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		this.addSlotToContainer(new SlotItemHandler(inventory, 1, 107+5, 31+5) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		
		// Inventory Slots
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		// HotBar Slots
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener) this.listeners.get(i);
			
			if (this.cookTime != this.tileEntity.getField(1))
				listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
			
			if (this.totalCookTime != this.tileEntity.getField(2))
				listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
		}
		
		this.cookTime = this.tileEntity.getField(1);
		this.totalCookTime = this.tileEntity.getField(2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileEntity.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	
	@Override 
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (fromSlot == 3) {
				if (!this.mergeItemStack(stack1, 4, 40, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (fromSlot != 2 && fromSlot != 1 && fromSlot != 0) {
				Slot slot1 = (Slot) this.inventorySlots.get(fromSlot + 1);

				if (!stack1.isEmpty()) {
					if (!this.mergeItemStack(stack1, 0, 2, false)) {
						return ItemStack.EMPTY;
					} else if (fromSlot >= 4 && fromSlot < 31) {
						if (!this.mergeItemStack(stack1, 31, 40, false))
							return ItemStack.EMPTY;
					} else if (fromSlot >= 31 && fromSlot < 40 && !this.mergeItemStack(stack1, 4, 31, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.mergeItemStack(stack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();

			}
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
	
}
