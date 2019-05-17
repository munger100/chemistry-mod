package com.yourri1012.chemistrymod.items.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class SplittingTable extends BlockBase {
	
	public SplittingTable(String name) {
		super(name, Material.ROCK);
		
		//Walking over the block
		setSoundType(SoundType.STONE);
		
		//Exactly like stone (Time to break)
		setHardness(1.5F);
		
		//we don't care if it doesn't explodes by creeper
		setResistance(1800000F);
		
		setHarvestLevel("pickaxe", 2);
		
		
		
	}
	
	public boolean isFullCube (IBlockState state) {
		return false;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}
}
