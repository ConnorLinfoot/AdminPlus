package com.connorlinfoot.adminplus.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChangeTimeMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Change Time - AdminPlus")) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Midnight")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setTime(18000);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Sunrise")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setTime(3000);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Midday")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setTime(6000);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Sunset")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setTime(9000);
                }
            }
            event.setCancelled(true);
        }
    }

}
