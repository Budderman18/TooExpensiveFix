package com.budderman18.TooExpensiveFix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class enables and disables the plugin
 * It also imports commands and handles events
 */
public class Main extends JavaPlugin implements Listener { 
    private final ConsoleCommandSender sender = getServer().getConsoleSender();
    private final String prefixMessage = ChatColor.translateAlternateColorCodes('&', "&a[&cTooExpensiveFix&a] ");
    /*
    *
    * Enables the plugin.
    * Checks if MC version isn't the latest.
    * If its not, warn the player about lacking support
    * Checks if server is running offline mode
    * If it is, disable the plugin
    * Also loads death event
    *
    */
    @Override
    public void onEnable() {
        //language variables
        final String unsupportedVersionAMessage = ChatColor.translateAlternateColorCodes('&', "&4TooExpensiveFix does not support your version!"); 
        final String unsupportedVersionBMessage = ChatColor.translateAlternateColorCodes('&', "&aOnly 1.18.2 is supported"); 
        final String unsupportedVersionCMessage = ChatColor.translateAlternateColorCodes('&', "&aThis plugin will likely not work and you will get no support for issues"); 
        final String unsecureServerAMessage = ChatColor.translateAlternateColorCodes('&', "&4NEVER EVER EVER EVER EVER EVER USE OFFLINE MODE!!!!!!!!!!!!!!!"); 
        final String unsecureServerBMessage = ChatColor.translateAlternateColorCodes('&', "&cOffline mode is a serious threat to your server and will never be supported by my plugins!"); 
        final String unsecureServerCMessage = ChatColor.translateAlternateColorCodes('&', "&cTo protect your safety and this plugin''s saftey, this plugin will now be disabled"); 
        final String pluginEnabledMessage = ChatColor.translateAlternateColorCodes('&', "&eTooExpensiveFix by Budderman18 has been enabled!"); 
        //check for correct version
        if (!(Bukkit.getVersion().contains("1.19"))) {
            sender.sendMessage(prefixMessage + unsupportedVersionAMessage);
            sender.sendMessage(prefixMessage + unsupportedVersionBMessage);
            sender.sendMessage(prefixMessage + unsupportedVersionCMessage);  
        }
        //check for online mode
        if (!(getServer().getOnlineMode())) {
            sender.sendMessage(prefixMessage + unsecureServerAMessage);
            sender.sendMessage(prefixMessage + unsecureServerBMessage);
            sender.sendMessage(prefixMessage + unsecureServerCMessage);
            getServer().getPluginManager().disablePlugin(this);
        }
        //events
        getServer().getPluginManager().registerEvents(this,this);
        //enable plugin
        getServer().getPluginManager().enablePlugin(this);
        sender.sendMessage(prefixMessage + pluginEnabledMessage);
    }
    /*
    *
    * This method disables the plugin
    *
    */
    @Override
    public void onDisable() {
        final String pluginDisabledMessage = ChatColor.translateAlternateColorCodes('&', "&eXPDeathDropFix by Budderman18 has been disabled!");
        //disables plugin
        getServer().getPluginManager().disablePlugin(this);
        sender.sendMessage(prefixMessage + pluginDisabledMessage);
    }
    /*
    *
    * This method hadles everything involving fixing the limit
    * It simply always sets the maximum to the level limit
    * It also sets renamed items to 1 level
    *
    */
    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent event) {
        event.getInventory().setMaximumRepairCost(21862);
        if (event.getInventory().getItem(2) != null) {
            if (event.getInventory().getItem(0).getItemMeta().getDisplayName() != event.getInventory().getItem(2).getItemMeta().getDisplayName() && event.getInventory().getItem(1) == null) {
                event.getInventory().setRepairCost(1);
            }
        }
    }
}
