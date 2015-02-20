package de.zerian.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;

public class Gamemode implements CommandExecutor {
	
	private Kurzy plugin;
	
	public Gamemode(Kurzy instance) {
		
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("gm"))  {
	      if (args.length == 0) {
	        p.sendMessage(Kurzy.getPrefix() + " §c/gm <0,1,2> <Spieler>");
	        return true;
	      }
	      if (args.length == 1) {
	        if (changeGameMode(p, args[0])) {
	          return true;
	        }
	      } else if (args.length == 2) {
	        Player t = Bukkit.getPlayer(args[1]);
	        if (t == null) {
	          p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
	          return true;
	        }
	        if (args[0].equalsIgnoreCase("0")) {
	          if (!p.hasPermission("kurzy.gamemode.other")) {
	            p.sendMessage(Kurzy.getPermissions());
	            return true;
	          }
	          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
	          t.setGameMode(GameMode.SURVIVAL);
	          p.sendMessage(Kurzy.getPrefix() + " §aDu hast " + pexPrefix + t.getName() + " §aden §aGamemode: §6Survial §agegeben");
	          return true;
	        }
	        if (args[0].equalsIgnoreCase("1")) {
	          if (!p.hasPermission("kurzy.gamemode.other")) {
	            p.sendMessage(Kurzy.getPermissions());
	            return true;
	          }
	          t.setGameMode(GameMode.CREATIVE);
	          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
	          p.sendMessage(Kurzy.getPrefix() + " §aDu hast " + pexPrefix + t.getName() + " §aden §aGamemode: §6Creative §agegeben");
	          return true;
	        }
	        if (args[0].equalsIgnoreCase("2")) {
	          if (!p.hasPermission("kurzy.gamemode.other")) {
	            p.sendMessage(Kurzy.getPermissions());
	            return true;
	          }
	          t.setGameMode(GameMode.ADVENTURE);
	          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
	          p.sendMessage(Kurzy.getPrefix() + " §aDu hast " + pexPrefix + t.getName() + " §aden §aGamemode: §6Adventure §agegeben");
	          return true;
	        }
	        p.sendMessage(Kurzy.getPrefix() + " §c/gm <0,1,2> <Spieler>");
	        return true;
	      }
	    }
	    return false;
	}
	  private boolean changeGameMode(Player p, String input) {
	    if (input.equalsIgnoreCase("0")) {
	      if (p.hasPermission("kurzy.gamemode"))
	      {
	        p.setGameMode(GameMode.SURVIVAL);
	        p.sendMessage(Kurzy.getPrefix() + " §aDu hast dir den §aGamemode: §6Survival §agegeben.");
	        return true;
	      }
	      p.sendMessage(Kurzy.getPermissions());
	      return true;
	    }
	    if (input.equalsIgnoreCase("1")) {
	      if (p.hasPermission("kurzy.gamemode")) {
	        p.setGameMode(GameMode.CREATIVE);
	        p.sendMessage(Kurzy.getPrefix() + " §aDu hast dir den §aGamemode: §6Creative §agegeben.");
	        return true;
	      }
	      p.sendMessage(Kurzy.getPermissions());
	      return true;
	    }
	    if (input.equalsIgnoreCase("2")) {
	      if (p.hasPermission("kurzy.gamemode")) {
	        p.setGameMode(GameMode.ADVENTURE);
	        p.sendMessage(Kurzy.getPrefix() + " §aDu hast dir den Gamemode: §6Adventure §agegeben.");
	        return true;
	      }
	      p.sendMessage(Kurzy.getPermissions());
	      return true;
	    }
		return false;
	}
	
	

}
