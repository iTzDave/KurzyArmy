package de.zerian.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.zerian.m.Kurzy;
import de.zerian.utils.Conf;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatManager
  implements Listener
{
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void chat(AsyncPlayerChatEvent e)
  {
    if (Conf.get().getBoolean("functions.chatm_enable") == true)
    {
      String format = Conf.get().getString("messages.chatm_format");
      if (PermissionsEx.getUser(e.getPlayer().getName()) == null) {
        return;
      }
      String prefix = PermissionsEx.getUser(e.getPlayer().getName()).getPrefix().replace("&", "§");
      String suffix = PermissionsEx.getUser(e.getPlayer().getName()).getSuffix().replace("&", "§");
      String msg = e.getMessage();
      String player = e.getPlayer().getDisplayName();
      if (e.getPlayer().hasPermission("kurzy.color")) {
        msg = msg.replace("&", "§");
      } else {
        msg = msg.replace("&", "");
      }
      format = 
      


        format.replace("{PREFIX}", prefix).replace("{NAME}", player).replace("{SUFFIX}", suffix).replace("{MESSAGE}", msg.replace("%", "")).replace("&", "§");
      
      e.setFormat(format);
    }
  }
}
