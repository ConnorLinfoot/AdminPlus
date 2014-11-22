package com.connorlinfoot.adminplus.Handlers;

import com.connorlinfoot.adminplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerMenu {

    public static Inventory playerMenuInventory(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, "Player Menu - AdminPlus");

        ItemStack is;
        ItemMeta im;

        ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
        ItemMeta blankM = blank.getItemMeta();
        blankM.setDisplayName(ChatColor.BLACK + "");
        blank.setItemMeta(blankM);

        // Top Left Main Menu Paper
        is = new ItemStack(Material.PAPER);
        im = (SkullMeta) is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Main Menu");
        is.setItemMeta(im);
        inv.setItem(0, is);

        // Change game mode icon (If set in config)
        if (Main.getInstance().getConfig().getBoolean("Change Game Mode.Enabled")) {
            is = new ItemStack(Material.WATCH);
            im = is.getItemMeta();
            im.setDisplayName(ChatColor.WHITE + "Change My Game Mode");
            is.setItemMeta(im);
            if (player.hasPermission("adminplus.changegamemode") || !Main.getInstance().getConfig().getBoolean("Change Game Mode.Use Perms"))
                inv.setItem(11, is);
        }

        Integer ii = 0;
        for (ItemStack i : inv.getContents()) {
            if (i == null) {
                inv.setItem(ii, blank);
            }
            ii++;
        }

        return inv;
    }

    public static void openPlayerMenu(Player player) {
        player.openInventory(playerMenuInventory(player));
    }

}
