package com.connorlinfoot.adminplus.Listeners;

import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChangeDifficultyMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("Change Difficulty - AdminPlus")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Peaceful")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.PEACEFUL);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Easy")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.EASY);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Normal")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.NORMAL);
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Hard")) {
                if (e.getWhoClicked() instanceof Player) {
                    Player p = (Player) e.getWhoClicked();
                    p.getWorld().setDifficulty(Difficulty.HARD);
                }
            }
            e.setCancelled(true);
        }
    }

}
