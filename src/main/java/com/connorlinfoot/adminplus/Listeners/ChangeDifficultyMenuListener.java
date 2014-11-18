package com.connorlinfoot.adminplus.Listeners;

import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChangeDifficultyMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Change Difficulty - AdminPlus")) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Peaceful")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.PEACEFUL);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Easy")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.EASY);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Normal")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.NORMAL);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Hard")) {
                if (event.getWhoClicked() instanceof Player) {
                    Player p = (Player) event.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.HARD);
                }
            }
            event.setCancelled(true);
        }
    }

}
