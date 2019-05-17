 package com.yourri1012.chemistrymod.util.handlers;

import com.yourri1012.chemistrymod.commands.CommandLobby;
import com.yourri1012.chemistrymod.commands.CommandPlayEasy;
import com.yourri1012.chemistrymod.commands.CommandPlayHard;
import com.yourri1012.chemistrymod.init.ModBlocks;
import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.init.PotionInit;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;
import com.yourri1012.chemistrymod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ModItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels(); 
			}
		}
		
		for(Block block : ModBlocks.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
		
	}
	
	public static void preInitRegistries (FMLPreInitializationEvent event) {
		PotionInit.registerPotions();
	}
	
	public static void serverRegistries(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandPlayEasy());
		event.registerServerCommand(new CommandPlayHard());
		event.registerServerCommand(new CommandLobby());
	}
}
