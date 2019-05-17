package com.yourri1012.chemistrymod.items.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class AlchemyTable extends BlockBase {
	public AlchemyTable(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10000000.0F);
		setHarvestLevel("axe", 0);
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
