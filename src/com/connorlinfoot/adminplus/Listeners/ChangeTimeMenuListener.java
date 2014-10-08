package com.connorlinfoot.adminplus.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChangeTimeMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("Change Time - AdminPlus")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Midnight")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setTime(0);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Sunrise")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setTime(3000);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Midday")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setTime(6000);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Sunset")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setTime(9000);
                }
            }
            e.setCancelled(true);
        }
    }

}
