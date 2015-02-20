package de.zerian.events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import de.zerian.utils.Conf;

public class VIPJoin
  implements Listener
{
  ArrayList<Player> joined = new ArrayList<Player>();
  boolean voll = false;
  Random r = new Random();
  
  
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    this.joined.add(e.getPlayer());
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e)
  {
    this.joined.remove(e.getPlayer());
  }
  
  @EventHandler(priority=EventPriority.HIGH)
  public void onLogin(PlayerLoginEvent e)
  {
    Player p = e.getPlayer();
    
    if(Conf.get().getBoolean("functions.vip") == true) {
    	
    if (p.hasPermission("kurzy.premium"))
    {
      if (Bukkit.getOnlinePlayers().length == Conf.get().getInt("functions.vipfunctionslots"))
      {
        for (Player s : this.joined) {
          if (s.hasPermission("kurzy.premium")) {
            this.voll = true;
          }
        }
        if (!this.voll)
        {
          int i = this.r.nextInt(Bukkit.getOnlinePlayers().length);
          Player kick = (Player)this.joined.get(i);
          if (!kick.hasPermission("kurzy.premium"))
          {
            kick.kickPlayer("§cDu wurdest gekickt um einem §6Premium§7-§6User §cPlatz zu machen");
            e.allow();
          }
        }
        else
        {
          e.disallow(null, "§cDer Server ist voll");
        }
      }
    }
    else if (Bukkit.getOnlinePlayers().length == Conf.get().getInt("functions.vipfunctionslots")) {
      e.disallow(null, "§cDer Server hat kein Platz für dich frei");
    }
    }
  }
  @EventHandler
  public void onLogin(ServerListPingEvent e) {
	  
	  int slots = Conf.get().getInt("functions.playerseenontheserver");
	  
	  e.setMaxPlayers(slots);
	
}
}