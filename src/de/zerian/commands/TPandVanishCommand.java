package de.zerian.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.zerian.m.Kurzy;
import de.zerian.utils.Conf;

public class TPandVanishCommand implements CommandExecutor,Listener {
	
	private Kurzy plugin;
	
	public TPandVanishCommand(Kurzy INSTANCE) {
		
		this.plugin = INSTANCE;
		
	}
	
	  public final static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	  public final static List<Player> isMoveing = new ArrayList<Player>();
	  public final static ArrayList<Player> tpaAnfrage = new ArrayList<Player>();
	  
	
	public static TPandVanishCommand INSTANCE;
	
	public static final Set<Player> vanish = new HashSet<Player>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		final Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("tphere")) {
			if(p.hasPermission("kurzy.tphere")) {
				if(args.length == 1) {
					Player ziel = Bukkit.getPlayer(args[0]);
					
	                if(Bukkit.getPlayer(args[0]) != null) {
						
					} else {
						p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
						return true;
					}
					
					p.sendMessage(Kurzy.getPrefix() + " §aTeleportation erfolgreich");
					ziel.teleport(p.getLocation());
				} else if(args.length == 0){
					p.sendMessage(Kurzy.getPrefix() + " §cUsage: /tphere <Spieler>");
					return true;
				}
			} else {
				p.sendMessage("§ce");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("tp")) {
			if(p.hasPermission("kurzy.tp")) {
				if(args.length == 2) {
					Player spieler1 = Bukkit.getPlayer(args[0]);
					Player spieler2 = Bukkit.getPlayer(args[1]);
					
                    if(Bukkit.getPlayer(args[0]) != null) {
						
					} else {
						p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
						return true;
					}
                    
                    if(Bukkit.getPlayer(args[1]) != null) {
						
					} else {
						p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
						return true;
					}
					p.sendMessage(Kurzy.getPrefix() + " §aTeleportation erfolgreich");
					spieler1.teleport(spieler2.getLocation());
				} else if (args.length == 0) {
					p.sendMessage(Kurzy.getPrefix() + " §cUsage: /tp <Spieler1> to <Spieler2> or /tp <Spieler>");
					return true;
				} else if(args.length == 1) {
					
					 if(Bukkit.getPlayer(args[0]) != null) {
							
						} else {
							p.sendMessage(Kurzy.getPrefix() + " §cDieser Spieler ist nicht online");
							return true;
						}
	                    
					p.sendMessage(Kurzy.getPrefix() + " §aTeleportation erfolgreich");
					p.teleport(Bukkit.getPlayer(args[0]).getLocation());
				}
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpall")) {
			if(p.hasPermission("kurzy.tpall")) {
				if(args.length == 0) {
					
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.teleport(p.getLocation());
					}
					p.sendMessage(Kurzy.getPrefix() + " §aTeleportation erfolgreich");
				}
			} else {
				Kurzy.noPermissions(p);
				return true;
			}
		}
		  
		   if (cmd.getName().equalsIgnoreCase("vanish")) {
			   if(p.hasPermission("kurzy.vanish")) {
              
               if (!vanish.contains(p)) {
                       for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                               pl.hidePlayer(p);
                               
                               if (pl.hasPermission("kurzy.teamchat")) {
            			              pl.sendMessage(Kurzy.team() + "§c" + p.getName() + " §ahat Vanish §2aktivert");
            			            }
                       }
                       
                       vanish.add(p);
                       p.sendMessage(Kurzy.getPrefix() + " §aVanish wurde §2aktivert");
                       return true;
               } else {
                       for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                               pl.showPlayer(p);
                               
                               if (pl.hasPermission("kurzy.teamchat")) {
           			              pl.sendMessage(Kurzy.team() + "§c" + p.getName() + " §ahat Vanish §cdeaktivert");
           			            }
                       }
                       
                          
                       vanish.remove(p);
                       p.sendMessage(Kurzy.getPrefix() + " §aVanish wurde §cdeaktivert");
                       return true;
               }
               } else {
            	   Kurzy.noPermissions(p);
            	   return true;
               }
		   }
		   if (cmd.getName().equalsIgnoreCase("tpa")) {
			   if(p.hasPermission("kurzy.tpa")) {
			      if (args.length == 1) {
			        final Player send = (Player)sender;
			        final Player target = Bukkit.getPlayer(args[0]);
			        if ((target != null) && (target.isOnline()))
			        {
			        	if(p != target) {
			          String pexPrefix = PermissionsEx.getUser(p).getPrefix().replace('&', '§');
			          if (tpa.containsKey(target)) {
			            send.sendMessage(pexPrefix + target.getName() + " §ahat noch eine gültige §3Teleportations§7-§3Anfrage");
			            return true;
			          }
			          tpa.put(target, p);
			          tpaAnfrage.add(p);
			          send.sendMessage("§8[§aTPA§8] §aAnfrage versendet");
			          
			          target.sendMessage(" ");
			          target.sendMessage("§8[§aTPA§8] " + pexPrefix + p.getName() + " §3möchte sich zu dir teleportieren");
			          target.sendMessage("§8[§aTPA§8] §2Annehmen §8[/tpaaccept]");
			          target.sendMessage("§8[§aTPA§8] §cAblehnen §8[/tpadeny]");
			          target.sendMessage("§8[§aTPA§8] §7Die Anfrage hält §630 §7Sekunden");
			          target.sendMessage(" ");
			          if (tpaAnfrage.contains(p)) {
			            Bukkit.getScheduler().scheduleAsyncDelayedTask(Kurzy.INSTANCE, new Runnable()
			            {
			              public void run()
			              {
			                TPandVanishCommand.tpa.remove(target);
			                TPandVanishCommand.tpa.remove(p);
			                tpaAnfrage.remove(p);
			                return;
			              }
			            }, 600L);
			          }
			        } else {
					    p.sendMessage("§8[§aTPA§8] §cDu kannst dich nicht zu dir selber teleportieren");
					   }
			        }  else {
			          send.sendMessage("§8[§aTPA§8] §cDieser Spieler ist nicht online");
			        }
			      } else if (args.length == 0) {
			        p.sendMessage("§cUsage: /tpa <Spieler>");
			      }
		   } else {
			   Kurzy.noPermissions(p);
		   }
			    }
			    if ((cmd.getName().equalsIgnoreCase("tpaaccept")) && 
			      (args.length == 0))
			    {
			      final Player target = (Player)tpa.get(p);
			      tpaAnfrage.remove(p);
			      if (!tpa.containsKey(p)) {
			        p.sendMessage("§8[§aTPA§8] §cMomentan möchte sich niemand zu dir teleportieren");
			        return true;
			      }
			      p.sendMessage("§8[§aTPA§8] §aAnfrage akzeptiert");
			      target.sendMessage("§8[§aTPA§8] §7Die Anfrage wurde §2akzeptiert");
			      
			      int delay = Conf.get().getInt("cooldowns.tpa_cooldown");
			      isMoveing.add(target);
			      target.sendMessage("§8[§aTPA§8] §3Die Teleportation beginnt in §6" + Conf.get().getInt("cooldowns.tpa_cooldown") + " §3Sekunden");
			      if (delay > 0) {
			        Bukkit.getScheduler().scheduleAsyncDelayedTask(Kurzy.INSTANCE, new Runnable()
			        {
			          public void run()
			          {
			            if (!TPandVanishCommand.isMoveing.contains(target)) {
			              target.sendMessage("§8[§aTPA§8] §cDer Teleport wurde durch deine Bewegung abgebrochen");
			              p.sendMessage("§8[§aTPA§8] §cDie Teleportation wurde abgebrochen");
			              tpa.remove(target);
			              tpa.remove(p);
			              isMoveing.remove(target);
			              return;
			            }
			            target.teleport(p.getLocation());
			            p.sendMessage("§8[§aTPA§8] §aTeleportation erfolgreich ausgeführt");
			            target.sendMessage("§8[§aTPA§8] §aTeleportation erfolgreich ausgeführt");
			            TPandVanishCommand.tpa.remove(target);
			            TPandVanishCommand.tpa.remove(p);
			            TPandVanishCommand.tpaAnfrage.remove(p);
			          }
			        }, delay * 20);
			      } else {
			        target.teleport(p.getLocation());
			      }
			    }
			    if ((cmd.getName().equalsIgnoreCase("tpadeny")) && 
			      (args.length == 0))
			    {
			      Player target = (Player)tpa.get(p);
			      if (!tpa.containsKey(p)) {
			        p.sendMessage("§8[§aTPA§8] §cMomentan möchte sich niemand zu dir teleportieren");
			        return true;
			      }
			      tpa.remove(target);
			      tpa.remove(p);
			      TPandVanishCommand.tpaAnfrage.remove(p);
			      p.sendMessage("§8[§aTPA§8] §3Die Anfrage wurde erfolgreich §cabgelehnt");
			      target.sendMessage("§8[§aTPA§8] §3Die Anfrage wurde leider §cabgelehnt");
			    }
		        return true;
		}
	public static List<Player> getMovePlayer() {
	    return TPandVanishCommand.isMoveing;
	  }
}