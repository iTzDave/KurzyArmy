package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(p.hasPermission("kurzy.fly")) {
				if(args.length == 0) {
					
					if(p.getAllowFlight()) {
						p.setAllowFlight(false);
						p.setFlying(false);
						p.sendMessage(Kurzy.getPrefix() + " §aDu kannst nun nicht mehr fliegen");
					} else {
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage(Kurzy.getPrefix() +" §aDu kannst nun fliegen");
					}
					
				} else if(args.length == 1) {
					
					if(p.hasPermission("kurzy.fly.other")) {
					
					
					if(Bukkit.getPlayer(args[0]) != null) {
						
					} else {
						p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
						return true;
					}
					
					Player target = Bukkit.getPlayer(args[0]);
					
						target.setAllowFlight(true);
						target.setFlying(true);
						String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
						p.sendMessage(Kurzy.getPrefix() +" §aDer Spieler §b" + pexPrefix + target.getName() +  " §akann nun fliegen");
						} else {
						p.sendMessage("§cNope");
						return true;
					}
				}
			} else {
				p.sendMessage("§cNope");
				return true;
			}
		}
		
		return false;
	}
	
	

}
