package com.connorlinfoot.adminplus;

import com.connorlinfoot.adminplus.Handlers.MainMenu;
import com.connorlinfoot.adminplus.Listeners.*;
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


public class Main extends JavaPlugin implements Listener {
    private static Plugin plugin;
    public static String Prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "AdminPlus" + ChatColor.GRAY + "] " + ChatColor.RESET;
    public static boolean SNAPSHOT = false;

    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        if (getConfig().isSet("Send Stats")) getConfig().set("Send Stats", null); // Remove Send Stats from config

        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
        if (getDescription().getVersion().contains("SNAPSHOT")) {
            SNAPSHOT = true;
            console.sendMessage(ChatColor.RED + "You are running a snapshot build of " + getDescription().getName() + " please report bugs!");
            console.sendMessage(ChatColor.RED + "NO support will be given if running old snapshot build!");
        }
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");

        Bukkit.getPluginManager().registerEvents(new MainMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new OPPlayerMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new BanPlayerMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new KickPlayerMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChangeTimeMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChangeDifficultyMenuListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("adminplus.use")) {
                MainMenu.openMainMenu(p);
            } else {
                p.sendMessage(Prefix + ChatColor.RED + "You do not have the permission " + ChatColor.BOLD + "\"adminplus.use\"");
            }
        } else {
            sender.sendMessage(Prefix + "Please use this command as a player");
        }
        return true;
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    @Deprecated
    public static Plugin getInstance() {
        return getPlugin();
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
