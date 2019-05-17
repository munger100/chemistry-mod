package com.yourri1012.chemistrymod.tileEntities;


import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.objectives.Gamemode;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityObjectiveTable extends TileEntity implements ICapabilityProvider, ITickable {
	protected ItemStackHandler handler= new ItemStackHandler(1);
	private String customName;
	private int time = 0;
	private int totalTime=60;
	private String name = "test";
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
	
	public boolean isItemValid(ItemStack stack) {
		for (ItemStack valid : this.valid_items) {
			if (valid.isItemEqualIgnoreDurability(stack)) {
				return true;
			}
		} return false;
	}
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.time=compound.getInteger("time");
		this.name=compound.getString("Name");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));

	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
	    NBTTagCompound compound = new NBTTagCompound();
	    compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("Time", (short)this.time);
		compound.setString("Name", this.name);	   
		return new SPacketUpdateTileEntity(getPos(), 1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
	    NBTTagCompound compound = pkt.getNbtCompound();
	    this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.time=compound.getInteger("time");
		this.name=compound.getString("Name");
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
	    return (oldState.getBlock() != newState.getBlock());
	}

	public boolean isRunning(){
		return this.handler.getStackInSlot(0) != ItemStack.EMPTY;
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("Objective Table");
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
 
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return this.world.getTileEntity(this.pos) != this ? false : playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void update() {
		if(isItemValid(this.handler.getStackInSlot(0))) {
			time++;
			if(time == totalTime) {
				check(name, handler.getStackInSlot(0).toString());
				handler.setStackInSlot(0, ItemStack.EMPTY);
				time=0;
			}
		} else time=0;
	}
	
	public void check(String name, String item) {
		if(world.isRemote)
			Gamemode.checkObjective(name, item);
	}
	
	public int getField(int i) {
		switch (i) {
			case 1: return this.time;
			default : return 0;
		}
		 
	}
	
	public void setField(int i, int j) {
		switch (i) {
			case 1: this.time=j;
			break;
		default : 
			return;
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
