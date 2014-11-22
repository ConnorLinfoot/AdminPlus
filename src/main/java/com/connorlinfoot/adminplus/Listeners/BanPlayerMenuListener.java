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

public class BanPlayerMenuListener implements Listener {

    HashMap<String, String> currentlyBannning = new HashMap<String, String>();

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Ban Player - AdminPlus")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Ban")) {
                String pName = event.getCurrentItem().getItemMeta().getDisplayName().substring(6);
                if (Bukkit.getPlayer(pName) == null) return;
                String command = Main.getPlugin().getConfig().getString("Kick Players.Command Format");
                boolean reason = false;
                if (command.contains("<reason>")) {
                    reason = true;
                }
                command = command.replace("<player>", pName);
                if (reason) {
                    currentlyBannning.put(p.getName(), pName);
                    event.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage(Main.Prefix + "Please type your reason in chat (This is not shown to players in actual chat)");
                    return;
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (currentlyBannning.containsKey(event.getPlayer().getName())) {
            String command = Main.getPlugin().getConfig().getString("Ban Players.Command Format");
            command = command.replace("<player>", currentlyBannning.get(event.getPlayer().getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("<reason>", event.getMessage()));
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.Prefix + currentlyBannning.get(event.getPlayer().getName()) + " has been banned!");
            currentlyBannning.remove(event.getPlayer().getName());
        }
    }

}
