package de.zerian.commands;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zerian.m.Kurzy;
import de.zerian.utils.Conf;
import de.zerian.utils.Home;

public final class HomeCommand implements CommandExecutor {
  private Kurzy plugin;
  
  public HomeCommand(Kurzy instance) {
    this.plugin = instance;
  }
  private static List<String> move = new ArrayList<String>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("home")) {
    	if(p.hasPermission("kurzy.home")) {
      if (!(sender instanceof Player)) {
    	  //When the Sender comes from the Console
        sender.sendMessage("Dieser Befehl ist nur für Spieler!");
      } else {
        final Player player = (Player)sender;
        if (args.length == 0) {
          if ((Home.get().isSet("users." + player.getName() + ".home.world")) && (Home.get().isSet("users." + player.getName() + ".home.x")) && (Home.get().isSet("users." + player.getName() + ".home.y")) && (Home.get().isSet("users." + player.getName() + ".home.z")))    {
           final Location loc = new Location(Bukkit.getServer().getWorld(Home.get().getString("users." + player.getName() + ".home.world")), Home.get().getDouble("users." + player.getName() + ".home.x"), Home.get().getDouble("users." + player.getName() + ".home.y"), Home.get().getDouble("users." + player.getName() + ".home.z"));
            loc.setYaw(Home.get().getInt("users." + player.getName() + ".home.d"));
            
            int delay = Conf.get().getInt("cooldowns.home_cooldown");
            if (delay > 0) {
              move.add(player.getName());
              player.sendMessage("§aDer §aTeleport beginnt in §b" + Conf.get().getInt("cooldowns.home_cooldown") + " §aSekunden, §cbitte warte...");
              Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                public void run() {
                  if (!move.contains(player.getName())) {
                    player.sendMessage(Kurzy.getPrefix() + " §cDu hast dich bewegt,der Teleport wurde abgebrochen");
                    return;
                  }
                  player.teleport(loc);
                  move.remove(player.getName());
                }
              }, delay * 20);
            } else {
              player.teleport(loc);
            }
            return true;
          } else {
            player.sendMessage(Kurzy.getPrefix() + " §cUm /home benutzen zu können musst du erstmal /sethome machen");
          }
        }
      }
      return true;
    } else {
    	Kurzy.noPermissions(p);
    	}	
    }
    if (cmd.getName().equalsIgnoreCase("sethome")) {
    	if(p.hasPermission("kurzy.sethome")) {
    		if (!(sender instanceof Player)) {
          sender.sendMessage("Dieser Befehl ist nur für Spieler!");
        } else {
          Player player = (Player)sender;
          Location loc = player.getLocation();
          if (args.length == 0) {
            Home.get().set("users." + player.getName() + ".home.world", loc.getWorld().getName());
            Home.get().set("users." + player.getName() + ".home.x", Double.valueOf(loc.getX()));
            Home.get().set("users." + player.getName() + ".home.y", Double.valueOf(loc.getY()));
            Home.get().set("users." + player.getName() + ".home.z", Double.valueOf(loc.getZ()));
            Home.get().set("users." + player.getName() + ".home.d", Float.valueOf(loc.getYaw()));
            player.sendMessage(Kurzy.getPrefix() + " §aDein Home wurde gesetzt und gespeichert");
            Home.save();
          }
        }
    } else {
    		Kurzy.noPermissions(p);
    		return true;
    	}
      }
	return false;
  }
  public static List<String> getMovePlayer()
  {
    return move;
  }
}
