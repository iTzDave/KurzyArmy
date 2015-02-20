package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class KillCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("kill")) {
			if(p.hasPermission("kurzy.kill")) {
				if(args.length == 0) {
					p.setHealth(0);
				} else if(args.length == 1){
					Player ziel = Bukkit.getPlayer(args[0]);
					
					if(p.hasPermission("kurzy.kill.other")) {
					
					 if(Bukkit.getPlayer(args[0]) != null) {
							
						} else {
							p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
							return true;
						}
					
					ziel.setHealth(0);
					String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
					p.sendMessage(Kurzy.getPrefix() + " §aDu hast §b" + pexPrefix + ziel.getName() + " §cgetötet");
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
			}
		} else {
			Kurzy.noPermissions(p);
			return true;
		}
		}
		return false;
	}

}
