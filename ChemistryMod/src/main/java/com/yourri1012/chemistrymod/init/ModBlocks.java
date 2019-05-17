package com.yourri1012.chemistrymod.init;

import java.util.ArrayList;
import java.util.List;

import com.yourri1012.chemistrymod.items.blocks.AlchemyTable;
import com.yourri1012.chemistrymod.items.blocks.BlockBase;
import com.yourri1012.chemistrymod.items.blocks.HotPlate;
import com.yourri1012.chemistrymod.items.blocks.ObjectiveTable;
import com.yourri1012.chemistrymod.items.blocks.SplittingTable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ALCHEMY_TABLE = new AlchemyTable("alchemy_table", Material.IRON);
	public static final Block SPLITTING_TABLE = new SplittingTable("splitting_table");
	public static final Block OBJECTIVE_TABLE = new ObjectiveTable("objective_table", Material.IRON);
	public static final Block HOT_PLATE = new HotPlate("hot_plate");
}
