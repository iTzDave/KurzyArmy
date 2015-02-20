package de.zerian.commands;

import java.util.ArrayList;
import java.util.List;










import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;

import de.zerian.m.Kurzy;
import de.zerian.utils.Conf;
import de.zerian.utils.Spawns;
import de.zerian.utils.Warps;

public class WarpCommand implements Listener, CommandExecutor {
  private Kurzy plugin;
  
  public WarpCommand(Kurzy instance) {
    this.plugin = instance;
    this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
  }
  
  static List<String> move1 = new ArrayList<>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  final Player player = (Player)sender;
	
	  if ((cmd.getName().equalsIgnoreCase("setwarp"))) {
		  if(player.hasPermission("kurzy.setwarp")) {
        if (args.length == 0) {
          player.sendMessage(Kurzy.getPrefix() + "§cUsage: /setwarp <warp-name>");
        } else if (args.length == 1) {
          try {
            String name = args[0].toString();
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY() + 1;
            int z = player.getLocation().getBlockZ();
            String w = player.getLocation().getWorld().getName().toString();
            Warps.get().set("Warps." + name + ".X", Integer.valueOf(x));
            Warps.get().set("Warps." + name + ".Y", Integer.valueOf(y));
            Warps.get().set("Warps." + name + ".Z", Integer.valueOf(z));
            Warps.get().set("Warps." + name + ".W", w);
            Warps.get().set("Warps." + name + ".yaw",Float.valueOf(player.getLocation().getYaw()));
            Warps.get().set("Warps." + name + ".pitch",Float.valueOf(player.getLocation().getPitch()));
            Warps.save();
            player.sendMessage(Kurzy.getPrefix() + " §aDer Warp §b" + args[0] + " §awurde erstellt");
          }
          catch (Exception localException)
          {
            player.sendMessage(Kurzy.getPrefix() + " §cERROR: §cWarps konnten nicht geladen werden");
          }
        }
		  } else {
	        	Kurzy.noPermissions(player);
	        	return true;
	        }
      } else if (cmd.getName().equalsIgnoreCase("warp")) {
        Server server = player.getServer();
        if ((args.length == 1))
          try {
            String warpname = args[0].toString();
            int delay = Conf.get().getInt("cooldowns.warp_cooldown");
            int x = Integer.parseInt(Warps.get().getString("Warps." + warpname + ".X"));
            int y = Integer.parseInt(Warps.get().getString("Warps." + warpname + ".Y"));
            int z = Integer.parseInt(Warps.get().getString("Warps." + warpname + ".Z"));
            World w = server.getWorld(Warps.get().getString("Warps." + warpname + ".W"));
            float yaw = (float)Warps.get().getDouble("Warps." + warpname + ".yaw");
            float pitch = (float)Warps.get().getDouble("Warps." + warpname + ".pitch");
            Warps.save();
            final Location loc = new Location(w, x, y, z);
            loc.setYaw(yaw);
            loc.setPitch(pitch);
            
            if (delay > 0)  {
              move1.add(player.getName());
              player.sendMessage("§aDer §aTeleport beginnt in §b" + Conf.get().getInt("cooldowns.warp_cooldown") + " §aSekunden, §cbitte warte...");
              Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                public void run() {
                  if (!WarpCommand.move1.contains(player.getName()))
                  {
                    player.sendMessage(Kurzy.getPrefix() + " §cDer Teleport wurde abgebrochen");
                    return;
                  }
                  player.teleport(loc);
                  WarpCommand.move1.remove(player.getName());
                }
              }, delay * 20);
            } else {
              player.teleport(loc);
            }
            return true;
          } catch (Exception exc){
            player.sendMessage(Kurzy.getPrefix()  + " §cDer Warp existiert nicht");
          }
      } else if (cmd.getName().equalsIgnoreCase("delwarp")) {
        if (args.length == 0) {
          player.sendMessage(Kurzy.getPrefix() + " §cUsage: /delwarp <warp-name>");
        }
        if ((args.length == 1) && 
          (player.hasPermission(new Permission("kurzy.delwarp"))))  {
          String warp = args[0].toString();
          Warps.get().set("Warps." + warp, null);
          Warps.save();
          player.sendMessage(Kurzy.getPrefix() + " §aDer Warp §b" + args[0] + " §awurde gelöscht");
        }
      }
	  return false;
  }
  public static List<String> getMovePlayer()
  {
    return move1;
  }
}