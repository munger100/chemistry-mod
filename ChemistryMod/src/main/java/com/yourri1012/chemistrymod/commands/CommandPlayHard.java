package com.yourri1012.chemistrymod.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.yourri1012.chemistrymod.objectives.Gamemode;
import com.yourri1012.chemistrymod.objectives.ObjectivesList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;

public class CommandPlayHard extends CommandBase {
	private final List<String> aliases = Lists.newArrayList("playhard", "ph", "hard");
	public static ObjectivesList objectives = new ObjectivesList();

	@Override
	public String getName() {
		return "playhard";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "playhard";
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
		if(sender instanceof EntityPlayer) {
			sender.sendMessage(new TextComponentString(TextFormatting.BLUE + "Hard Mode: Started."));
			for (EntityPlayer p : server.getPlayerList().getPlayers()) {
				p.setPositionAndUpdate(-350.5, 4.5, 8.5);
				p.setGameType(GameType.ADVENTURE);
				p.getMaxHealth();
				p.getFoodStats().setFoodLevel(20);
				p.sendMessage(new TextComponentString(objList.list.get(0).toString()));
			}
			Gamemode.setServer(server);
			Gamemode.setHard();
		}
	}
}
