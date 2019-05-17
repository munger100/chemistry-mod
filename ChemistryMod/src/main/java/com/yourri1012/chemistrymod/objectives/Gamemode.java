package com.yourri1012.chemistrymod.objectives;

import com.yourri1012.chemistrymod.init.PotionInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class Gamemode  {
	public static MinecraftServer server;
	public static int level;
	public static int botLevel;
	public static long initTime;
	public static long currentTime;
	public static int maxTimer;
	public static EntityPlayer p;
	public static boolean playing;
	public static boolean t3;
	public static boolean t2;
	public static boolean t1;

	public static void setServer(MinecraftServer mServer) {
		server = mServer;
		level = 0;
		p=server.getPlayerList().getPlayers().get(0);
		t1=true;
		t2=true;
		t3=true;
	}
	
	public static void reset() {
		level = 0;
		botLevel = 0;
		maxTimer = 999999999;
		playing=false;
		t1=true;
		t2=true;
		t3=true;
	}
	
	public static void setEasy() {
		initTime = server.getEntityWorld().getTotalWorldTime();
		maxTimer = 21000 ;
		botLevel = 1;
		p.inventory.clear();
		playing=true;
		t1=true;
		t2=true;
		t3=true;
	}
	
	public static void setHard() {
		initTime = server.getEntityWorld().getTotalWorldTime();

		maxTimer = 14000;
		botLevel = 1;
		p.inventory.clear();
		playing=true;
		t1=true;
		t2=true;
		t3=true;


	}
	
	public static void checkObjective(String playerName, String itemName) {
		p = (EntityPlayer)server.getPlayerList().getPlayerByUsername(playerName);
		String message = "";
		if ((itemName.equals(ObjectivesList.list.get(level).solution)  || itemName.equals("1xitem.objective_skip@0") )) {
			level++;
			if(level<10) {
				message = "Success! Objective " + (level + 1)+ " : " + ObjectivesList.list.get(level).toString();
			}
		} else message = "Nope! Try again.";

		if(level == 10) {
			p.sendMessage(new TextComponentString("Congratulations! " + p.getName() + " has won! Going back to lobby"));
			p.setPositionAndUpdate(8.5, 4.5, 8.5);
			playing=false;
			return;
		}
		
		p.sendMessage(new TextComponentString(message));
		
		if(level > botLevel) {
			p.sendMessage(new TextComponentString("Nice! You are ahead of the Yourri by " + (level - botLevel) + " objective(s)!"));
		} else if(level < botLevel) {
			p.sendMessage(new TextComponentString("Uh oh! The Yourri is " + (botLevel - level) + " objectives ahead of you! Hurry up!"));
		} else if(level == botLevel) {
			p.sendMessage(new TextComponentString("Game's tight! You and the Yourri are at the same objective!"));
		}
	}

	@SubscribeEvent
	public static void bot(PlayerTickEvent event) {
		if (playing) {
			currentTime = server.getEntityWorld().getTotalWorldTime();
			
			if (botLevel == 10) {
				p.sendMessage(new TextComponentString("Oh no! The Yourri has won! Hehehe."));
				p.sendMessage(new TextComponentString("Telporting in 3 seconds"));
				botLevel=20;
				initTime=currentTime;
			} else if (botLevel == 20) {
				if ((int)(currentTime-initTime) == 20 && t3) {
					p.sendMessage(new TextComponentString("3"));
					t3=false;
				} else if ((int)(currentTime-initTime) == 40 && t2) {
					p.sendMessage(new TextComponentString("2"));
					t2=false;
				} else if ((int)(currentTime-initTime) == 60 && t1) {
					p.sendMessage(new TextComponentString("1"));
					p.setPositionAndUpdate(8.5, 4.5, 8.5);
					p.getMaxHealth();
					p.getFoodStats().setFoodLevel(20);
					playing=false;
					t1=false;
				}
			} else {
				if ((int)(currentTime-initTime) == maxTimer/10) {
					initTime=currentTime;
					botLevel++;
					p.sendMessage(new TextComponentString("Yourri gained a level! He is now level " + botLevel));
				}
			}
		}
	}
}
