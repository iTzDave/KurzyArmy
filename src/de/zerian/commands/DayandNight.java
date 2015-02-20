package de.zerian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zerian.m.Kurzy;

public class DayandNight implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("day")) {
			if(p.hasPermission("kurzy.day")) {
				if(args.length == 0) {
				    p.getWorld().setTime(0);
					p.sendMessage(Kurzy.getPrefix() + " §aZeit erfolgreich geändert");
				}
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("night")) {
			if(p.hasPermission("kurzy.night")) {
				if(args.length == 0) {
				    p.getWorld().setTime(13500);
					p.sendMessage(Kurzy.getPrefix() + " §aZeit erfolgreich geändert");
				}
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
		}
		return false;
	}
}
