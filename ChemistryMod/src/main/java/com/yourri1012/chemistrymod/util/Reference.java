package com.yourri1012.chemistrymod.util;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Reference {
	// General
	public static final String MOD_ID = "cm";
	public static final String NAME = "chemistry mod";
	public static final String Version = "1.0";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.yourri1012.chemistrymod.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.yourri1012.chemistrymod.proxy.CommonProxy";
	
	// GUI
	public static final int GUI_HOT_PLATE = 1;
	public static final int GUI_OBJECTIVE_TABLE=0;
	
	public static final SimpleNetworkWrapper Instance = NetworkRegistry.INSTANCE.newSimpleChannel("cm");
}
