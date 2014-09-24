package com.connorlinfoot.adminplus.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ChangeTimeMenu {

    public static Inventory changeTimeMenuInventory(Player p){
        Inventory inv = Bukkit.createInventory( p,9,"Change Time - AdminPlus" );

        ItemStack is;
        ItemMeta im;

        is = new ItemStack(Material.WATCH,1);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Midnight (0)");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.WATCH,2);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Sunrise (3000)");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.WATCH,3);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Midday (6000)");
        is.setItemMeta(im);
        inv.addItem(is);

        is = new ItemStack(Material.WATCH,4);
        im = is.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Sunset (9000)");
        is.setItemMeta(im);
        inv.addItem(is);

        return inv;
    }

    public static void openChangeTimeMenu(Player p){
        p.openInventory(changeTimeMenuInventory(p));
    }

}
