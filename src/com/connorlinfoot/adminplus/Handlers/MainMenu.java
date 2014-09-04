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

public class MainMenu {

    public static Inventory mainMenuInventory(Player p){
        Inventory inv = Bukkit.createInventory( p,45,"AdminPlus - V" + Main.getInstance().getDescription().getVersion() );

        ItemStack is;
        ItemMeta im;

        ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 7);
        ItemMeta blankM = blank.getItemMeta();
        blankM.setDisplayName(ChatColor.BLACK + "");
        blank.setItemMeta(blankM);

        // Top Left Player Head
        is = new ItemStack(Material.SKULL_ITEM);
        is.setDurability((short) 3);
        SkullMeta sm = (SkullMeta) is.getItemMeta();
        sm.setOwner(p.getName());
        sm.setDisplayName(ChatColor.WHITE + "My Controls");
        is.setItemMeta(sm);
        inv.setItem(0,is);


        // Kick a Player icon (If set in config)
        if( Main.getInstance().getConfig().getBoolean("Kick Players.Enabled") ) {
            is = new ItemStack(Material.STONE_AXE);
            im = is.getItemMeta();
            im.setDisplayName(ChatColor.WHITE + "Kick a Player");
            is.setItemMeta(im);
            inv.setItem(20, is);
        }


        // Ban a Player icon (If set in config)
        if( Main.getInstance().getConfig().getBoolean("Ban Players.Enabled") ) {
            is = new ItemStack(Material.GOLD_AXE);
            im = is.getItemMeta();
            im.setDisplayName(ChatColor.WHITE + "Ban a Player");
            is.setItemMeta(im);
            inv.setItem(21, is);
        }

        Integer ii = 0;
        for( ItemStack i : inv.getContents() ){
            if( i == null ){
                inv.setItem(ii,blank);
            }
            ii++;
        }

        return inv;
    }

    public static void openMainMenu(Player p){
        p.openInventory(mainMenuInventory(p));
    }

}
