package com.yourri1012.chemistrymod.util.handlers;

import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;
import com.yourri1012.chemistrymod.util.Reference;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	@SuppressWarnings("deprecation")
	public static void registerTileEntities() {
		ResourceLocation RESOURCE = new ResourceLocation(Reference.MOD_ID, "TileEntityHotPlate");
		ResourceLocation RESOURCE2 = new ResourceLocation(Reference.MOD_ID, "ObjectiveTable");
		GameRegistry.registerTileEntity(TileEntityObjectiveTable.class, "objective_table");
		GameRegistry.registerTileEntity(TileEntityHotPlate.class, "TileEntityHotPlate");
	}

}
