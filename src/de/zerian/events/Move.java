package de.zerian.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.zerian.commands.HomeCommand;
import de.zerian.commands.SpawnCommand;
import de.zerian.commands.TPandVanishCommand;
import de.zerian.commands.WarpCommand;
import de.zerian.m.Kurzy;

public class Move implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		
		
		if(SpawnCommand.getMovePlayer().contains(e.getPlayer().getName())){
			SpawnCommand.getMovePlayer().remove(e.getPlayer().getName());
		}
		if(WarpCommand.getMovePlayer().contains(e.getPlayer().getName())){
			WarpCommand.getMovePlayer().remove(e.getPlayer().getName());
		}
		if(HomeCommand.getMovePlayer().contains(e.getPlayer().getName())){
			HomeCommand.getMovePlayer().remove(e.getPlayer().getName());
		}
		if(TPandVanishCommand.getMovePlayer().contains(e.getPlayer())) {
			TPandVanishCommand.getMovePlayer().remove(e.getPlayer());
		}
	}
}
