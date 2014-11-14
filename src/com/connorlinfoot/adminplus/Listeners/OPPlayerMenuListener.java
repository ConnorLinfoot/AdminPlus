package com.connorlinfoot.adminplus.Listeners;

import com.connorlinfoot.adminplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OPPlayerMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("OP Player - AdminPlus")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("OP")) {
                String pName = event.getCurrentItem().getItemMeta().getDisplayName().substring(5);
                if (Bukkit.getPlayer(pName) == null) return;
                Bukkit.getPlayer(pName).setOp(true);
                p.sendMessage(Main.Prefix + pName + " is now op");
            }
            event.setCancelled(true);
        }
    }

}
