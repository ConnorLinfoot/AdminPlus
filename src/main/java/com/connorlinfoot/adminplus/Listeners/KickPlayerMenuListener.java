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

    HashMap<String, String> currentlyKicking = new HashMap<String, String>();

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Kick Player - AdminPlus")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Kick")) {
                String pName = event.getCurrentItem().getItemMeta().getDisplayName().substring(7);
                String command = Main.getPlugin().getConfig().getString("Kick Players.Command Format");
                boolean reason = false;
                if (command.contains("<reason>")) {
                    reason = true;
                }
                command = command.replace("<player>", pName);

                if (reason) {
                    currentlyKicking.put(p.getName(), pName);
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
    public void onChat(final AsyncPlayerChatEvent event) {
        if (currentlyKicking.containsKey(event.getPlayer().getName())) {
            String command = Main.getPlugin().getConfig().getString("Kick Players.Command Format");
            command = command.replace("<player>", currentlyKicking.get(event.getPlayer().getName()));
            final String finalCommand = command;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand.replaceAll("<reason>", event.getMessage()));
                }
            }, 1L);
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.Prefix + currentlyKicking.get(event.getPlayer().getName()) + " has been kicked!");
            currentlyKicking.remove(event.getPlayer().getName());
        }
    }

}
