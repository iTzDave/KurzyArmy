package de.zerian.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import de.zerian.m.Kurzy;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TabListener
  implements Listener {
	
	
  private Kurzy plugin;
  
  public TabListener(Kurzy instance) {
    this.plugin = instance;
  }
  
  @EventHandler
  public void join(PlayerJoinEvent e) {
    final Player p = e.getPlayer();
    String color = "";
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&b")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&b"))) {
      color = color + "§b";
    } else if(p.hasPermission("kurzy.&b") && !p.isOp()) {
        color = color + "§b";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&0")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&0"))) {
      color = color + "§0";
    } else if(p.hasPermission("kurzy.&0") && !p.isOp()) {
    	 color = color + "§0";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&9")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&9"))) {
      color = color + "§9";
    } else if(p.hasPermission("kurzy.&9") && !p.isOp()) {
    	color = color + "§9";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&3")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&3"))) {
      color = color + "§3";
    } else if(p.hasPermission("kurzy.&3") && !p.isOp()) {
    	 color = color + "§3";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&1")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&1"))) {
      color = color + "§1";
    } else if(p.hasPermission("kurzy.&1") && !p.isOp()) {
    	color = color + "§1";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&8")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&8"))) {
      color = color + "§8";
    } else if(p.hasPermission("kurzy.&8") && !p.isOp()) {
    	color = color + "§8";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&2")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&2"))) {
      color = color + "§2";
    } else if(p.hasPermission("kurzy.&2") && !p.isOp()) {
    	 color = color + "§2";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&6")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&6"))) {
      color = color + "§6";
    } else if(p.hasPermission("kurzy.&6") && !p.isOp()) {
    	 color = color + "§6";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&7")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&7"))) {
      color = color + "§7";
    } else if(p.hasPermission("kurzy.&7") && !p.isOp()) {
    	 color = color + "§7";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&a")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&2"))) {
      color = color + "§a";
    } else if(p.hasPermission("kurzy.&a") && !p.isOp()) {
    	color = color + "§a";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&d")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&d"))) {
      color = color + "§d";
    } else if(p.hasPermission("kurzy.&d") && !p.isOp()) {
    	 color = color + "§d";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&c")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&c"))) {
      color = color + "§c";
    } else if(p.hasPermission("kurzy.&c") && !p.isOp()) {
    	color = color + "§c";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&e")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&e"))) {
      color = color + "§e";
    } else if(p.hasPermission("kurzy.&e") && !p.isOp()) {
    	 color = color + "§e";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&f")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&f"))) {
      color = color + "§f";
    }  else if(p.hasPermission("kurzy.&f") && !p.isOp()) {
    	color = color + "§f";
    }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&5")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&5"))) {
      color = color + "§5";
    } else if(p.hasPermission("kurzy.&5") && !p.isOp()) {
    	color = color + "§5";
  }
    if ((PermissionsEx.getUser(p.getName()).has("kurzy.&4")) && (!PermissionsEx.getUser(p.getName()).has("-kurzy.&4"))) {
      color = color + "§4";
    } else if(p.hasPermission("kurzy.&4") && !p.isOp()) {
    	color = color + "§4";
    }
    String now = color + p.getName();
    if (now.length() > 16) {
      now = now.substring(0, 15);
    }
    final String s = now;
    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        p.setPlayerListName(s);
      }
    }, 5L);
  }
}