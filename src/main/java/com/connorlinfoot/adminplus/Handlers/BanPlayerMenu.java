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

public class BanPlayerMenu {

    public static Inventory banMenuInventory(Player player) {
        //Work out players for inv size
        Integer invSize = 0;
        if (Bukkit.getOnlinePlayers().length <= 9) {
            invSize = 9;
        } else if (Bukkit.getOnlinePlayers().length <= 18) {
            invSize = 18;
        } else if (Bukkit.getOnlinePlayers().length <= 27) {
            invSize = 27;
        } else if (Bukkit.getOnlinePlayers().length <= 36) {
            invSize = 36;
        } else if (Bukkit.getOnlinePlayers().length <= 45) {
            invSize = 45;
        } else {
            invSize = 54;
        }

        Inventory inv = Bukkit.createInventory(player, invSize, "Ban Player - AdminPlus");

        ItemStack is;
        ItemMeta im;

        Integer i = 0;
        for (Player pp : Bukkit.getOnlinePlayers()) {
            if (i == 55) {
                break;
            }
            is = new ItemStack(Material.SKULL_ITEM);
            is.setDurability((short) 3);
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.setOwner(pp.getName());
            sm.setDisplayName(ChatColor.WHITE + "Ban " + pp.getName());
            is.setItemMeta(sm);
            inv.setItem(i, is);
            i++;
        }

        if (i == 55) {
            player.sendMessage(Main.Prefix + "Oops, too many players, will fix this when I'm less lazy");
        }

        return inv;
    }

    public static void openBanMenu(Player player) {
        player.openInventory(banMenuInventory(player));
    }

}
