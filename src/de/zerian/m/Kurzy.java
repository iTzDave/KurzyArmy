package de.zerian.m;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.zerian.commands.BCandTeamChatCommand;
import de.zerian.commands.DayandNight;
import de.zerian.commands.ECandInvseeCommand;
import de.zerian.commands.FlyCommand;
import de.zerian.commands.Gamemode;
import de.zerian.commands.HealCommand;
import de.zerian.commands.HomeCommand;
import de.zerian.commands.KillCommand;
import de.zerian.commands.MSGandChatClearCommand;
import de.zerian.commands.SpawnCommand;
import de.zerian.commands.TPandVanishCommand;
import de.zerian.commands.WarpCommand;
import de.zerian.events.ChatManager;
import de.zerian.events.CompressEvent;
import de.zerian.events.JoinQuitListener;
import de.zerian.events.Move;
import de.zerian.events.RespawnDeathListener;
import de.zerian.events.SignColor;
import de.zerian.events.TabListener;
import de.zerian.events.VIPJoin;
import de.zerian.mysql.MySQL;
import de.zerian.utils.Conf;
import de.zerian.utils.Home;
import de.zerian.utils.Spawns;
import de.zerian.utils.Warps;

public class Kurzy extends JavaPlugin implements Listener {
	
	private MySQL sql;
	public Kurzy k;
	public static Kurzy INSTANCE;
	
	@Override
	public void onEnable() {

		//Config Registery
		Spawns.create();
		Spawns.load();
		
		Conf.get().set("functions.spawn_onjoin", false);
		Conf.get().set("functions.spawn_firstjoined", false);
		Conf.get().set("functions.spawn_ondeath", false);
		Conf.get().set("functions.vip", false);
		Conf.get().set("functions.vipfunctionslots", 0);
		Conf.get().set("functions.playerseenontheserver", 0);
		Conf.get().set("functions.chatm_enable", true);
		Conf.get().set("functions.join_message", false);
		Conf.get().set("functions.quit_message", false);
		Conf.get().set("functions.death_messages", false);
		
		Conf.get().set("cooldowns.spawn_cooldown", 0);
		Conf.get().set("cooldowns.warp_cooldown", 0);
		Conf.get().set("cooldowns.tpa_cooldown", 0);
		Conf.get().set("cooldowns.home_cooldown", 0);
		Conf.get().set("cooldowns.home_cooldown", 0);
		
		
		Conf.get().set("messages.chatm_format", "{PREFIX}{NAME} {SUFFIX} {MESSAGE}");
		Conf.get().set("messages.deathnormal_message", "§7%spieler% §aist gestorben");
		Conf.get().set("messages.deathbyplayer_message", "§7%spieler% §awurde von §c%killer% §agetötet");
		
		Conf.create();
		Conf.load();
		
		Warps.create();
		Warps.load();
		
		Home.create();
		Home.save();
		
		//Event Registery
		Bukkit.getServer().getPluginManager().registerEvents(new Move(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CompressEvent() , this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChatManager() , this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignColor(this) , this);
		Bukkit.getServer().getPluginManager().registerEvents(new VIPJoin() , this);
		Bukkit.getServer().getPluginManager().registerEvents(new TabListener(this) , this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinQuitListener() , this);
		Bukkit.getServer().getPluginManager().registerEvents(new RespawnDeathListener() , this);
		
		INSTANCE = this;
		
		
		
		
		//Command Registery
		
		
		new HomeCommand(this);
		
		getCommand("sethome").setExecutor(new HomeCommand(this));
		getCommand("home").setExecutor(new HomeCommand(this));
		
		new ECandInvseeCommand();
		
		getCommand("enderchest").setExecutor(new ECandInvseeCommand());
		getCommand("invsee").setExecutor(new ECandInvseeCommand());
		getCommand("clear").setExecutor(new ECandInvseeCommand());
		
		new HealCommand();
		
		getCommand("heal").setExecutor(new HealCommand());
		
		new TPandVanishCommand(this);
		
		getCommand("tphere").setExecutor(new TPandVanishCommand(this));
		getCommand("tp").setExecutor(new TPandVanishCommand(this));
		getCommand("tpall").setExecutor(new TPandVanishCommand(this));
		getCommand("tpa").setExecutor(new TPandVanishCommand(this));
		getCommand("tpaaccept").setExecutor(new TPandVanishCommand(this));
		getCommand("tpadeny").setExecutor(new TPandVanishCommand(this));
		getCommand("vanish").setExecutor(new TPandVanishCommand(this));
		
		new MSGandChatClearCommand(this);
		
		getCommand("chat").setExecutor(new MSGandChatClearCommand(this));
		
		
		new BCandTeamChatCommand();
		
		getCommand("bc").setExecutor(new BCandTeamChatCommand());
		getCommand("tc").setExecutor(new BCandTeamChatCommand());
		
		new DayandNight();
		
		getCommand("day").setExecutor(new DayandNight());
		getCommand("night").setExecutor(new DayandNight());
		
		new FlyCommand();
		
		getCommand("fly").setExecutor(new FlyCommand());
		
		new Gamemode(this);
		
		getCommand("gm").setExecutor(new Gamemode(this));
		
		new SpawnCommand(this);
		
		getCommand("setspawn").setExecutor(new SpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		
		new KillCommand();
		
		getCommand("kill").setExecutor(new KillCommand());
		
		new WarpCommand(this);
		
		getCommand("warp").setExecutor(new WarpCommand(this));
		getCommand("setwarp").setExecutor(new WarpCommand(this));
		getCommand("delwarp").setExecutor(new WarpCommand(this));
		
		
	}
	@Override
	public void onDisable() {
		Spawns.save();
		Warps.save();
		Home.save();
	}
	
	public static String getPrefix(){
		return "§8[§6KurzyArmy§8]";
	}
	  public static Player noPermissions(Player p) {
		  
		  p.sendMessage("§cNope");
		  
		  return p;
	  }
	public static String getPermissions() {
		return "§cNope";
	}
	public static String team() {
		return "§8[§4Team§8] ";
	}
}
