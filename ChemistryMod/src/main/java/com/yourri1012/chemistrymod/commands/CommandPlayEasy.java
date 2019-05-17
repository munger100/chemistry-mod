	package com.yourri1012.chemistrymod.commands;

import java.util.List;

import com.google.common.collect.Lists;

import com.yourri1012.chemistrymod.objectives.*;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;

public class CommandPlayEasy extends CommandBase {
	private final List<String> aliases = Lists.newArrayList("playeasy", "pe", "ezpz");

	@Override
	public String getName() {
		return "playeasy";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "playeasy";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	public List<String> getAliases(){
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		ObjectivesList objList = new ObjectivesList();	
		for (Objective o: objList.list) {
			System.out.println(o.toString());
		}
		if (sender instanceof EntityPlayer) {
			sender.sendMessage(new TextComponentString(TextFormatting.BLUE + "Easy Mode: Started."));
			for (EntityPlayer p : server.getPlayerList().getPlayers()) {
				p.setPositionAndUpdate(350.5, 8.5, 8.5);
				p.setGameType(GameType.ADVENTURE);
				p.getMaxHealth();
				p.getFoodStats().setFoodLevel(20);
				p.sendMessage(new TextComponentString(objList.list.get(0).toString()));
			}
			Gamemode.setServer(server);
			Gamemode.setEasy();
		}
	}
}
