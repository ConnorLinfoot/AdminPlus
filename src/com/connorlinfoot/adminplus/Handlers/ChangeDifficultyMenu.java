package com.connorlinfoot.adminplus.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChangeDifficultyMenu {

    public static Inventory changeDifficultyMenuInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9, "Change Difficulty - AdminPlus");

        ItemStack is;
        ItemMeta im;

        is = new ItemStack(Material.MOB_SPAWNER, 1);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Peaceful");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.MOB_SPAWNER, 2);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Easy");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.MOB_SPAWNER, 3);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Normal");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.MOB_SPAWNER, 4);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Hard");
        is.setItemMeta(im);
        inv.addItem(is);

        return inv;
    }

    public static void openChangeDifficultyMenu(Player p) {
        p.openInventory(changeDifficultyMenuInventory(p));
    }

}
