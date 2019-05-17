package com.yourri1012.chemistrymod.items.blocks;

import java.util.Random;

import com.yourri1012.chemistrymod.Main;
import com.yourri1012.chemistrymod.init.ModBlocks;
import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HotPlate extends BlockBase{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool BURNING = PropertyBool.create("burning");
	
	public HotPlate(String name) {
		super(name, Material.ROCK);
		
		//Walking over the block
		setSoundType(SoundType.STONE);
		
		//Exactly like stone (Time to break)
		setHardness(1.5F);
		
		//we don't care if it doesn't explodes by creeper
		setResistance(1800000F);
		
		setHarvestLevel("pickaxe", 2);

		this.setDefaultState(this.blockState.getBaseState().withProperty(BURNING, true));
	}
	
	public boolean isFullCube (IBlockState state) { return false; }
	
	public boolean isOpaqueCube(IBlockState state) { return false; }
	
	public boolean renderAsNormalBlock() { return false; }
	
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityHotPlate();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.HOT_PLATE);
	}
	
	@Override 
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModBlocks.HOT_PLATE);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUI_HOT_PLATE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		if (!worldIn.isRemote) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}
	
	public TileEntityHotPlate getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TileEntityHotPlate)world.getTileEntity(pos);
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, ModBlocks.HOT_PLATE.getDefaultState().withProperty(BURNING, active), 3);
		
		if (tileEntity != null) {
			tileEntity.validate();
			worldIn.setTileEntity(pos, tileEntity);
		}	
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState());
	}	
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityHotPlate tileEntity = (TileEntityHotPlate)worldIn.getTileEntity(pos);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {BURNING, FACING});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
		
	}
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}
	
	
}
