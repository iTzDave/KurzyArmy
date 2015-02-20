package de.zerian.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.commands.TPandVanishCommand;
import de.zerian.m.Kurzy;
import de.zerian.utils.Conf;
import de.zerian.utils.Spawns;

public class JoinQuitListener implements Listener {
	
	@EventHandler
    public void join(PlayerJoinEvent e) {
		 final Player p = e.getPlayer();
		 
		 String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
		    
		    if(Conf.get().getBoolean("functions.spawn_firstjoined") == true) {
		    	
		    	if(!e.getPlayer().hasPlayedBefore()) {
		    		
				          double x = Spawns.get().getDouble("spawn.x");
				          double y = Spawns.get().getDouble("spawn.y");
				          double z = Spawns.get().getDouble("spawn.z");
				          double yaw = Spawns.get().getDouble("spawn.yaw");
				          double pitch = Spawns.get().getDouble("spawn.pitch");
				          String world = Spawns.get().getString("spawn.world");
				          
				          Location loc = new Location(Bukkit.getWorld(world), x, y, z);
				          loc.setYaw((float)yaw);
				          loc.setPitch((float)pitch);
				          
				          p.teleport(loc);
		    		
		    	}
		    }
		    for (Player v : TPandVanishCommand.vanish) {
	            e.getPlayer().hidePlayer(v);
	    }
		    
		    p.setAllowFlight(false);
		    p.setFlying(false);
		    
		    
		    if(Conf.get().getBoolean("functions.join_message") == true) {
		    	
		    	e.setJoinMessage("§a[+] "+ pexPrefix + p.getName() );
		    	
		    }
		    
	        if(Conf.get().getBoolean("functions.join_message") == false) {
		    	
		    	e.setJoinMessage("§e" + p.getName()+ " §ehat das Spiel betreten");
		    	
		    }
		    
		    
		    if(Conf.get().getBoolean("functions.spawn_onjoin") == true) {
				
				 Bukkit.getScheduler().scheduleSyncDelayedTask(Kurzy.INSTANCE, new Runnable()
			      {
			        public void run()
			        {
			          double x = Spawns.get().getDouble("spawn.x");
			          double y = Spawns.get().getDouble("spawn.y");
			          double z = Spawns.get().getDouble("spawn.z");
			          double yaw = Spawns.get().getDouble("spawn.yaw");
			          double pitch = Spawns.get().getDouble("spawn.pitch");
			          String world = Spawns.get().getString("spawn.world");
			          
			          Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			          loc.setYaw((float)yaw);
			          loc.setPitch((float)pitch);
			          
			          p.teleport(loc);
			        }
			      }, 5L);
				}
		    if(p.getGameMode() == GameMode.SURVIVAL) {
		    	p.setAllowFlight(false);
			    p.setFlying(false);
		    }
		    if(p.getGameMode() == GameMode.ADVENTURE) {
		    	p.setAllowFlight(false);
			    p.setFlying(false);
		    }
		    if(p.getGameMode() == GameMode.CREATIVE) {
		    	p.setAllowFlight(true);
			    p.setFlying(true);
		    }
	}
	@EventHandler
	  public void onLeave(PlayerQuitEvent e) {
		final Player p = e.getPlayer();
	    
      TPandVanishCommand.vanish.remove(e.getPlayer());
      String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
      if(Conf.get().getBoolean("functions.quit_message") == true) {
	    	
	    	e.setQuitMessage("§c[-] " + pexPrefix + p.getName() );
	    	
	    }
	    
      if(Conf.get().getBoolean("functions.quit_message") == false) {
	    	
	    	e.setQuitMessage("§e" + p.getName()+ " §ehat das Spiel verlassen");
	    	
	    }
	}
}
