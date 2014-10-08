package com.connorlinfoot.adminplus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OPPlayerMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("OP Player - AdminPlus")) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("OP")) {
                String pName = e.getCurrentItem().getItemMeta().getDisplayName().substring(5);
                if (Bukkit.getPlayer(pName) == null) return;
                Bukkit.getPlayer(pName).setOp(true);
                p.sendMessage(pName + " is now op");
            }
            e.setCancelled(true);
        }
    }

}
