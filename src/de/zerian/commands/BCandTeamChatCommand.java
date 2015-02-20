package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class BCandTeamChatCommand implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("bc")) {
			if(p.hasPermission("kurzy.broadcast")) {
				 if (args.length >= 1)
			        {
			          String nachricht = "";
			          for (int i = 0; i < args.length; i++)
			          {
			            nachricht = nachricht + " " + args[i];
			            nachricht = ChatColor.translateAlternateColorCodes('&', nachricht);
			          }
			          Bukkit.broadcastMessage("§8[§4§l!§8]"+ " §b" + nachricht);
			          return true;
			} else if(args.length == 0) {
				p.sendMessage(Kurzy.getPrefix() + " §cUsage: /bc <Nachricht> or /broadcast <Nachricht>");
				return true;
			}
		  } else {
			  Kurzy.noPermissions(p);
			  return true;
		  }
		}
		if(cmd.getName().equalsIgnoreCase("tc")) {
			if(p.hasPermission("kurzy.teamchat")) {
				 if (args.length >= 1)  {
			          String nachricht = "";
			          for (int i = 0; i < args.length; i++) {
			            nachricht = nachricht + " " + args[i];
			            nachricht = ChatColor.translateAlternateColorCodes('&', nachricht);
			          }
			          for (Player online : Bukkit.getOnlinePlayers()) {
			            if (online.hasPermission("kurzy.teamchat")) {
			            	String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
			              online.sendMessage(Kurzy.INSTANCE.team() + pexPrefix + p.getName() + " §7> §b" + nachricht);
			            }
			          }
			          return true;
			} else {
				p.sendMessage(Kurzy.getPrefix() + " §cUsage: /tc <Nachricht>");
				return true;
				}
			}
		}
		return false;
	}
}
