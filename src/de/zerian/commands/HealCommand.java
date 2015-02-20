package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("heal")) {
			if(p.hasPermission("kurzy.heal")) {
				if(args.length == 0) {
					
					if(p.getGameMode() == GameMode.CREATIVE) {
						p.sendMessage(Kurzy.getPrefix() + " §cDu kannst dich nicht heilen,da du im §6Creative Mode §cbist");
						return true;
					}
					
					p.setHealth(20.0);
					p.setFoodLevel(20);
					p.sendMessage(Kurzy.getPrefix() + " §aDu wurdest geheilt");
					
					
					
				}else if(args.length == 1) {
					Player ziel = Bukkit.getPlayer(args[0]);
					
					if(Bukkit.getPlayer(args[0]) != null) {	
						
					} else {
						p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
						return true;
					}
					
					ziel.setHealth(20.0);
					String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
					ziel.setFoodLevel(20);
					p.sendMessage(Kurzy.getPrefix() + " §aDu hast den Spieler §b" + pexPrefix + ziel.getName() + " §ageheilt");
				}
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
		}
		return false;
	}

}
