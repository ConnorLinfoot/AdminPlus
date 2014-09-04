package com.connorlinfoot.adminplus.Listeners;

import com.connorlinfoot.adminplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class KickPlayerMenuListener implements Listener {

    HashMap<String,String> currentlyKicking = new HashMap<String, String>();

    @EventHandler
    public void onMainMenuClick( InventoryClickEvent e ){
        if( e.getInventory().getTitle().equals("Kick Player - AdminPlus") ){
            if( e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR ) return;
            Player p = (Player) e.getWhoClicked();
            if( e.getCurrentItem().getItemMeta().getDisplayName().contains("Kick") ){
                String pName = e.getCurrentItem().getItemMeta().getDisplayName().substring(7);
                String command = Main.getInstance().getConfig().getString("Kick Players.Command Format");
                boolean reason = false;
                if( command.contains("<reason>") ){
                    reason = true;
                }
                command = command.replace("<player>",pName);

                if( reason ){
                    currentlyKicking.put(p.getName(),pName);
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage("Please type your reason in chat (This is not shown to players in actual chat)");
                    return;
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat( AsyncPlayerChatEvent e ){
        if( currentlyKicking.containsKey(e.getPlayer().getName()) ){
            String command = Main.getInstance().getConfig().getString("Kick Players.Command Format");
            command = command.replace("<player>",currentlyKicking.get(e.getPlayer().getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<reason>",e.getMessage()));
            e.setCancelled(true);
            e.getPlayer().sendMessage(currentlyKicking.get(e.getPlayer().getName()) + " has been kicked!");
            currentlyKicking.remove(e.getPlayer().getName());
        }
    }

}
