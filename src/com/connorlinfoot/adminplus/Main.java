package com.connorlinfoot.adminplus;

import com.connorlinfoot.adminplus.Handlers.MainMenu;
import com.connorlinfoot.adminplus.Listeners.BanPlayerMenuListener;
import com.connorlinfoot.adminplus.Listeners.KickPlayerMenuListener;
import com.connorlinfoot.adminplus.Listeners.MainMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class Main extends JavaPlugin implements Listener {
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        if(getConfig().getBoolean( "Send Stats" )) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
                console.sendMessage("MC Stats (Metrics) is enabled!");
            } catch (IOException e) {
                // Failed to submit the stats :-( <-- Dat face doe
            }
        }

        console.sendMessage(ChatColor.GREEN + "============ AdminPlus! ============");
        console.sendMessage(ChatColor.GREEN + "=========== VERSION: " + getDescription().getVersion() + " ===========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");

        Bukkit.getPluginManager().registerEvents(new MainMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new BanPlayerMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new KickPlayerMenuListener(), this);


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("adminplus.use")) {
                MainMenu.openMainMenu(p);
            } else {
                p.sendMessage(ChatColor.RED + "You do not have the permission " + ChatColor.BOLD + "\"adminplus.use\"");
            }
        } else {
            sender.sendMessage("Please use this command as a player");
        }
        return true;
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
