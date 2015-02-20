package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class ECandInvseeCommand implements CommandExecutor{
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("enderchest")) {
			if(args.length == 0) {
				p.sendMessage(Kurzy.getPrefix() + " §cUsage: /enderchest <Spieler>");
			}
	      if (args.length == 1) {
	        if (p.hasPermission("kurzy.enderchest")) {
	          Player t = Bukkit.getPlayer(args[0]);
	          if (t == null) {
	            p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
	            return true;
	          }
	          p.openInventory(t.getEnderChest());
	          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
	          p.sendMessage(Kurzy.getPrefix() + " §aDu siehst nun die §5Enderchest §avon " + pexPrefix + t.getName());
	          return true;
	        }
	        p.sendMessage(Kurzy.getPermissions());
	        return true;
	      }
	      return true;
	    }
		 if (cmd.getName().equalsIgnoreCase("invsee")) {
		      if (p.hasPermission("kurzy.invseee")) {
		    	  if(args.length == 0) {
		    		  p.sendMessage(Kurzy.getPrefix() + " §cUsage: /invsee <Spieler>");
		    	  }
		        if (args.length == 1) {
		          String name = args[0];
		          Player t = Bukkit.getPlayer(name);
		          if (t == null) {
		            p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
		            return true;
		          }
		          p.openInventory(t.getInventory());
		          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
		          p.sendMessage(Kurzy.getPrefix() + " §aDu siehst nun das §3Inventar §avon " + pexPrefix + t.getName());
		          return true;
		        }
		      } else {
		      p.sendMessage(Kurzy.getPermissions());
		      return true;
		      }
		    }
		 if (cmd.getName().equalsIgnoreCase("clear")) {
		      if (p.hasPermission("kurzy.clear")) {
		        if (args.length == 0) {
		          p.sendMessage(Kurzy.getPrefix() + " §cUsage: /clear <Spieler>");
		        } else if (args.length == 1) {
		          Player p2 = Bukkit.getPlayer(args[0]);
		          if (Bukkit.getPlayer(args[0]) == null)
		          {
		            p.sendMessage(Kurzy.getPrefix() + " §cDer Spieler ist nicht offline");
		            return true;
		          }
		          p2.getInventory().clear();
		          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
		          p.sendMessage(Kurzy.getPrefix() + " §aDu hast das §3Inv §avon : " + pexPrefix + p2.getName() + " §agecleart");
		        }
		      } else {
		        Kurzy.getPermissions();
		        return true;
		      }
		    }
		return false;
	}
}
