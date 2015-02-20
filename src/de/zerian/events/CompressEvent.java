package de.zerian.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CompressEvent implements Listener {
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent e){
		if(!e.getPlayer().hasPermission("kurzy.plugins.see")){
			if(e.getMessage().toLowerCase().startsWith("/pl") ||
					e.getMessage().toLowerCase().startsWith("/plugins") ||
					e.getMessage().toLowerCase().startsWith("/?") ||
					e.getMessage().toLowerCase().startsWith("/plugin")) {
				e.getPlayer().sendMessage("§fPlugins (1): §aKurzyArmy");
				e.setCancelled(true);
				
			}
		}
		if(!e.getPlayer().hasPermission("kurzy.plugins.see")){
			
			if(e.getMessage().toLowerCase().startsWith("/help") ||
					e.getMessage().toLowerCase().startsWith("/bukkit:help")) {
						e.getPlayer().sendMessage("§e------------------------------------");
						e.getPlayer().sendMessage("§9TeamSpeak§7: §3TS.KurzyArmy.de");
						e.getPlayer().sendMessage("§2Forum§7: §3http://KurzyArmy.de");
						e.getPlayer().sendMessage("§6Shop§7: §3http://shop.KurzyArmy.de");
						e.getPlayer().sendMessage("§bTwitter§7: §3http://twitter.com/KurzyArmy");
						e.getPlayer().sendMessage("§e------------------------------------");
						
						e.setCancelled(true);
			}
			
		}
		if(e.getMessage().toLowerCase().startsWith("/me") ||
				    e.getMessage().toLowerCase().startsWith("/bukkit:me") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:w") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:tell") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:rl") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:reload") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:stop") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:op") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:kill") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:msg") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:pl") ||
			        e.getMessage().toLowerCase().startsWith("/bukkit:plugins")||
			        e.getMessage().toLowerCase().startsWith("/bukkit:?")) {
			e.getPlayer().sendMessage("§cNope");
			e.setCancelled(true);{
				
			}
			
			}
	}
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("?")){
			if(!sender.hasPermission("kurzy.commands.see")){
				List<String> l = new ArrayList<>();
				
				return l;
			}
		}
		return null;
	}

}
