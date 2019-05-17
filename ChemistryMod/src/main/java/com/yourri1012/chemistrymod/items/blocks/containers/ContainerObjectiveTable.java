package com.yourri1012.chemistrymod.items.blocks.containers;

import com.yourri1012.chemistrymod.items.blocks.slots.ObjectiveTableSlots;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerObjectiveTable extends Container {
	private final TileEntityObjectiveTable tileentity;
	private IItemHandler inventory;
	private int time, totalTime;
	private String name;
	
	public ContainerObjectiveTable(EntityPlayer player, TileEntityObjectiveTable tileentity) {
		this.tileentity=tileentity;
		this.inventory = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new ObjectiveTableSlots(this.inventory,0, 80, 36));
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player.inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player.inventory, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);
		ItemStack previous = ItemStack.EMPTY;;
		if (slot != null && slot.getHasStack()) {
			slot.putStack(ItemStack.EMPTY);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getPosition().distanceSq(tileentity.getPos().add(0.5, 0.5, 0.5)) <=64;
	}

	
}
