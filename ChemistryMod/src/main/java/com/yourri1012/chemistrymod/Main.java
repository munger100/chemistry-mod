package com.yourri1012.chemistrymod;

import com.yourri1012.chemistrymod.objectives.Gamemode;
import com.yourri1012.chemistrymod.proxy.CommonProxy;
import com.yourri1012.chemistrymod.tabs.ChemistryTab;
import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;
import com.yourri1012.chemistrymod.util.Reference;
import com.yourri1012.chemistrymod.util.handlers.GuiHandler;
import com.yourri1012.chemistrymod.util.handlers.RegistryHandler;
import com.yourri1012.chemistrymod.world.WorldEvents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.Version)
public class Main {
	@Instance
	public static Main instance;
	public static final CreativeTabs chemistrytab = new ChemistryTab("chemistrytab");
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
 		RegistryHandler.preInitRegistries(event);
		GameRegistry.registerTileEntity(TileEntityObjectiveTable.class, Reference.MOD_ID + ":TileEntityObjectiveTable");
		GameRegistry.registerTileEntity(TileEntityHotPlate.class, Reference.MOD_ID + ":TileEntityHotPlate");
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}	
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) {
		RegistryHandler.serverRegistries(event);
	}
	
	@EventHandler
	public static void WorldEvent (PlayerTickEvent event) {
		WorldEvents.firePotionActive(event);
		WorldEvents.antiGravityActive(event);
		Gamemode.bot(event);
	}
	
	@EventHandler
	public static void spawnEvent(PlayerLoggedInEvent event) {
		WorldEvents.spawn(event);
	}
	
	@EventHandler
	public static void reSpawnEvent(PlayerRespawnEvent event) {
		WorldEvents.reSpawn(event);
		
	}
}
