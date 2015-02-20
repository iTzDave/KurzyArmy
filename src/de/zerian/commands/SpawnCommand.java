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
import de.zerian.utils.Spawns;
import de.zerian.utils.Conf;

public class SpawnCommand
  implements CommandExecutor
{
  private Kurzy plugin;
  
  public SpawnCommand(Kurzy instance)
  {
    this.plugin = instance;
  }
	  
  
  static List<String> move = new ArrayList<String>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }
    final Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("setspawn")) {
      if (p.hasPermission("kurzy.setspawn")) {
        Spawns.get().set("spawn.x", Double.valueOf(p.getLocation().getX()));
        Spawns.get().set("spawn.y", Double.valueOf(p.getLocation().getY()));
        Spawns.get().set("spawn.z", Double.valueOf(p.getLocation().getZ()));
        Spawns.get().set("spawn.world", p.getWorld().getName());
        Spawns.get().set("spawn.yaw", Float.valueOf(p.getLocation().getYaw()));
        Spawns.get().set("spawn.pitch", Float.valueOf(p.getLocation().getPitch()));
        Spawns.save();
        p.sendMessage(Kurzy.getPrefix() + " §aDer Spawn wurde erfolgreich gesetzt");
        return true;
      }
      return true;
    }
    if ((cmd.getName().equalsIgnoreCase("spawn")) && 
      (p.hasPermission("kurzy.spawn")))  {
      if (!Spawns.get().isSet("spawn")) {
        p.sendMessage(Kurzy.getPrefix() + " §cDer Spawn ist nicht gesetzt");
        return true;
      }
      if (args.length == 0) {
        try {
          int delay = Conf.get().getInt("cooldowns.spawn_cooldown");
          double x = Spawns.get().getDouble("spawn.x");
          double y = Spawns.get().getDouble("spawn.y");
          double z = Spawns.get().getDouble("spawn.z");
          double yaw = Spawns.get().getDouble("spawn.yaw");
          double pitch = Spawns.get().getDouble("spawn.pitch");
          String world = Spawns.get().getString("spawn.world");
          
          final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
          loc.setYaw((float)yaw);
          loc.setPitch((float)pitch);
          if (delay > 0)
          {
            move.add(p.getName());
            p.sendMessage("§aDer §aTeleport beginnt in §b" + Conf.get().getInt("cooldowns.spawn_cooldown") + " §aSekunden, §cbitte warte...");
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
              public void run() {
                if (!SpawnCommand.move.contains(p.getName())) {
                  p.sendMessage(Kurzy.getPrefix() + " §cDu hast dich bewegt,der Teleport wurde abgebrochen");
                  return;
                }
                p.teleport(loc);
                SpawnCommand.move.remove(p.getName());
              }
            }, delay * 20);
          } else {
            p.teleport(loc);
          }
          return true;
        }
        catch (NumberFormatException e)
        {
          p.sendMessage(Kurzy.getPrefix());
        }
      } else {
        if (args.length == 1)  {
          if (!p.hasPermission("kurzy.spawn.other")) {
            return true;
          }
          Player t = Bukkit.getPlayer(args[0]);
          if (t == null) {
            p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist offline");
            return true;
          }
          double x = Spawns.get().getDouble("spawn.x");
          double y = Spawns.get().getDouble("spawn.y");
          double z = Spawns.get().getDouble("spawn.z");
          double yaw = Spawns.get().getDouble("spawn.yaw");
          double pitch = Spawns.get().getDouble("spawn.pitch");
          String world = Spawns.get().getString("spawn.world");
          
          Location loc = new Location(Bukkit.getWorld(world), x, y, z);
          loc.setYaw((float)yaw);
          loc.setPitch((float)pitch);
          
          t.teleport(loc);
          return true;
        }
        p.sendMessage(Kurzy.getPrefix() + " §c/spawn <Spieler>");
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