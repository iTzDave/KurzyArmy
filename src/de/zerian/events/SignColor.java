package de.zerian.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import de.zerian.m.Kurzy;

public class SignColor implements Listener{
	
	private Kurzy plugin;;

	public SignColor(Kurzy instance){
		
		this.plugin = instance;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	@EventHandler 
	public void color(SignChangeEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("kurzy.color")){
			return;
		}
		for(int i = 0; i < e.getLines().length;i++){
			if(e.getLine(i).isEmpty()){
				continue;
			}
			if(e.getLine(i).contains("&")){
				e.setLine(i, e.getLine(i).replace("&", "§"));
			}
		}
	}
}
