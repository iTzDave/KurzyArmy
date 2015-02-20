package de.zerian.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.utils.Conf;
import de.zerian.utils.Spawns;

public class RespawnDeathListener implements Listener {
	
	 @EventHandler
	  public void repawn(PlayerRespawnEvent e) {
	if(Conf.get().getBoolean("functions.spawn_ondeath") == true) {
	      double x = Spawns.get().getDouble("spawn.x");
	      double y = Spawns.get().getDouble("spawn.y");
	      double z = Spawns.get().getDouble("spawn.z");
	      double yaw = Spawns.get().getDouble("spawn.yaw");
	      double pitch = Spawns.get().getDouble("spawn.pitch");
	      String world = Spawns.get().getString("spawn.world");
	      
	      Location loc = new Location(Bukkit.getWorld(world), x, y, z);
	      loc.setYaw((float)yaw);
	      loc.setPitch((float)pitch);
	      
	      e.setRespawnLocation(loc);
	}

	  }
	  @EventHandler
	  public void onDeath(final PlayerDeathEvent e) {
		  
		  if(Conf.get().getBoolean("functions.death_messages") == true) {
		  
		  
		  if(e.getEntity().getKiller() instanceof Player){
				String s = Conf.get().getString("messages.deathbyplayer_message");
				if(s == null || s.isEmpty() || s.equalsIgnoreCase("''")){
					e.setDeathMessage(null);
					return;
				}
				String pexPrefix = PermissionsEx.getUser(e.getEntity()).getPrefix().replace('&', '§');
				s = s.replace("%spieler%", pexPrefix + e.getEntity().getName());
				s = s.replace("%killer%", pexPrefix + e.getEntity().getKiller().getName());
				s = s.replace("&", "§");
				e.setDeathMessage(s);
			} else {
				String s = Conf.get().getString("messages.deathnormal_message");
				if(s == null || s.isEmpty() || s.equalsIgnoreCase("''")){
					e.setDeathMessage(null);
					return;
				}
				s = s.replace("%spieler%", e.getEntity().getName());
				s = s.replace("&", "§");
				e.setDeathMessage(s);
			}
			}
		  
	  }

}
