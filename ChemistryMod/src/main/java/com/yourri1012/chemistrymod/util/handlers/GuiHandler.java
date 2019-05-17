package com.yourri1012.chemistrymod.util.handlers;

import com.yourri1012.chemistrymod.items.blocks.containers.ContainerHotPlate;
import com.yourri1012.chemistrymod.items.blocks.containers.ContainerObjectiveTable;
import com.yourri1012.chemistrymod.items.blocks.gui.GuiHotPlate;
import com.yourri1012.chemistrymod.items.blocks.gui.GuiObjectiveTable;
import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos= new BlockPos(x,y,z);
		if (ID == Reference.GUI_HOT_PLATE) 
			return new ContainerHotPlate(player.inventory, (TileEntityHotPlate) world.getTileEntity(pos));
		
		if (ID == Reference.GUI_OBJECTIVE_TABLE) {
			return new ContainerObjectiveTable (player, (TileEntityObjectiveTable) world.getTileEntity(pos));
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos= new BlockPos(x,y,z);
		if (ID == Reference.GUI_HOT_PLATE) 
			return new GuiHotPlate(player, (TileEntityHotPlate) world.getTileEntity(pos));
		if(ID == Reference.GUI_OBJECTIVE_TABLE) 
			return new GuiObjectiveTable (player, (TileEntityObjectiveTable) world.getTileEntity(new BlockPos(x,y,z)));

		return null;
	}

}
