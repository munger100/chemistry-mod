package com.yourri1012.chemistrymod.tileEntities;
import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.items.blocks.HotPlate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityHotPlate extends TileEntity implements ITickable {
	private ItemStackHandler handler = new ItemStackHandler(2);
	private String customName;
	private int cookTime;
	private int totalCookTime;
	
	public TileEntityHotPlate() {
		super();
		this.cookTime = 0;
		this.totalCookTime = 100;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("Hot Plate");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		 
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
	    return (oldState.getBlock() != newState.getBlock());
	}
	
	public boolean isBurning() {
		// Always burning fuel, no fuel needed because it is electric.
		return this.handler.getStackInSlot(0) != ItemStack.EMPTY;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityHotPlate te) {
		return te.handler.getStackInSlot(0) != ItemStack.EMPTY;
	}
	
	@Override
	public void update() {	
		HotPlate.setState(true, world, pos);
		ItemStack input = handler.getStackInSlot(0);
		ItemStack output = handler.getStackInSlot(1);
		ItemStack result = ItemStack.EMPTY;
		
		if (input.isEmpty()) {
			this.cookTime = 0;
			return;
		}
		switch (input.getDisplayName()) {
			case "Copper":
				result = new ItemStack(ModItems.MOLTEN_COPPER);
				break;
			case "Platinum":
				result = new ItemStack(ModItems.MOLTEN_PLATINUM);
				break;
			case "Emerald":
				result = new ItemStack(ModItems.MOLTEN_EMERALD);
				break;
			case "Iron Ingot":
				result = new ItemStack(ModItems.MOLTEN_IRON);
				break;
			case "Gold Ingot":
				result = new ItemStack(ModItems.MOLTEN_GOLD);
				break;
			case "Diamond":
				result = new ItemStack(ModItems.MOLTEN_DIAMOND);
				break;
		}
		if (result != ItemStack.EMPTY) {
			cookTime++;
			if(cookTime>=totalCookTime) {
				if (output == ItemStack.EMPTY) {
					handler.setStackInSlot(1, result);
				} else if (!output.isItemEqual(result)) {
					return;
				} else {
					handler.getStackInSlot(1).grow(1);
				}
				handler.getStackInSlot(0).shrink(1);
				cookTime=0;
			}
		}
	}
	
	public int getField(int id) {
		switch(id) {
			case 1:
				return this.cookTime;
			case 2:
				return this.totalCookTime;
			default:
				return 0;
		}
	}

	public void setField(int id, int value) {
		if (id == 1) {
			this.cookTime = value;
		} else if (id == 2) {
			this.totalCookTime = value;
		}
	}
}
