package com.connorlinfoot.adminplus.Listeners;

import com.connorlinfoot.adminplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerMenuListener implements Listener {

    @EventHandler
    public void onPlayerMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Player Menu - AdminPlus")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            final Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Change My Game Mode")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage("Yeah I'm working on this...");
                    }
                }, 1L);
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }

}
