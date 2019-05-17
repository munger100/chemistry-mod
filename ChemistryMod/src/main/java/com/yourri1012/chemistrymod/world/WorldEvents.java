package com.yourri1012.chemistrymod.world;

import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.init.PotionInit;
import com.yourri1012.chemistrymod.objectives.Gamemode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class WorldEvents {
	@SubscribeEvent
	public static void firePotionActive(PlayerTickEvent event) {
		boolean isActive =false;
		
		final EntityPlayer p = event.player;
		ItemStack chest= p.inventory.armorInventory.get(2);
		ItemStack chestcheck=new ItemStack(ModItems.LAB_COAT);
		if(p.isPotionActive(PotionInit.FIRE_POTION_EFFECT )) isActive=true;
			if(isActive) 
			{
				if(chest.toString().equals(chestcheck.toString()) ) {
						event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 10));
					
						event.player.setFire(1);
						
				}
				else {
					event.player.setFire(1);	
				}
				
			}
	}
	
	
	
	@SubscribeEvent
	public static void antiGravityActive(PlayerTickEvent event) {
		boolean isActive =false;
		float speed = event.player.getAIMoveSpeed();
		if(event.player.isPotionActive(PotionInit.ANTI_GRAVITY_EFFECT)) isActive=true;
		if(isActive){
			event.player.jump();
			event.player.isEntityInvulnerable(new DamageSource("FALL"));
		}
		
	}
	
	@SubscribeEvent
	public static void spawn(PlayerLoggedInEvent event) {
		if(event.player instanceof EntityPlayer) {
			event.player.setPositionAndUpdate(8.5, 4.5, 8.5);
			Gamemode.playing=false;
			event.player.sendMessage(new TextComponentString("Open book in chest to have info on how to play the game!"));
		}
	}
	
	@SubscribeEvent
	public static void reSpawn(PlayerRespawnEvent event) {
		event.player.setPositionAndUpdate(8.5, 4.5, 8.5);
		Gamemode.playing=false;
		event.player.sendMessage(new TextComponentString("You died! Be more careful with dangerous stuff. You can try again if you want, just use the preffered command!"));

	}
	
	
}
