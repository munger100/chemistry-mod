package com.yourri1012.chemistrymod.commands;

import java.util.List;

import com.google.common.collect.Lists;

import com.yourri1012.chemistrymod.objectives.Gamemode;

import com.yourri1012.chemistrymod.objectives.ObjectivesList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;

public class CommandLobby extends CommandBase {
	private final List<String> aliases = Lists.newArrayList("lobby");

	@Override
	public String getName() {
		return "lobby";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "lobby";
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
		if(sender instanceof EntityPlayerMP) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Game stopped."));
			for (EntityPlayerMP p : server.getPlayerList().getPlayers()) {
				p.setPositionAndUpdate(8.5, 4.5, 8.5);
				p.setGameType(GameType.ADVENTURE);
				p.inventory.clear();
				p.getMaxHealth();
				p.getFoodStats().setFoodLevel(20);
			}
			ObjectivesList.reset();
			Gamemode.reset();
		}
	}
}

