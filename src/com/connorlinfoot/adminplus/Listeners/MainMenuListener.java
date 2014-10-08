package com.connorlinfoot.adminplus.Listeners;

import com.connorlinfoot.adminplus.Handlers.*;
import com.connorlinfoot.adminplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("AdminPlus - V" + Main.getInstance().getDescription().getVersion())) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
            final Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Change Time")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        ChangeTimeMenu.openChangeTimeMenu(p);
                    }
                }, 1L);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Change Difficulty")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        ChangeDifficultyMenu.openChangeDifficultyMenu(p);
                    }
                }, 1L);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("OP a Player")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        OPPlayerMenu.openOPMenu(p);
                    }
                }, 1L);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Ban a Player")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        BanPlayerMenu.openBanMenu(p);
                    }
                }, 1L);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kick a Player")) {
                p.closeInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        KickPlayerMenu.openKickMenu(p);
                    }
                }, 1L);
            } else {
                e.setCancelled(true);
            }
        }
    }

}
