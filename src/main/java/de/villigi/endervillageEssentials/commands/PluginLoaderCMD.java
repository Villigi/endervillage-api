package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginLoaderCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("essentials.pluginloader")) {
            if(strings.length == 2) {
                if(strings[1].equalsIgnoreCase("enable")){
                    String pluginName = strings[0];
                    if(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName) != null) {
                        Essentials.getInstance().getServer().getPluginManager().enablePlugin(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName));
                        p.sendMessage(Essentials.prefix + "§aDas Plugin '" + pluginName + "' wurde aktiviert.");
                    } else {
                        p.sendMessage(Essentials.prefix + "§cDas Plugin '" + pluginName + "' ist nicht installiert.");
                    }
                }else if(strings[1].equalsIgnoreCase("disable")){
                    String pluginName = strings[0];
                    if(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName) != null) {
                        Essentials.getInstance().getServer().getPluginManager().disablePlugin(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName));
                        p.sendMessage(Essentials.prefix + "§aDas Plugin '" + pluginName + "' wurde deaktiviert.");
                    } else {
                        p.sendMessage(Essentials.prefix + "§cDas Plugin '" + pluginName + "' ist nicht installiert.");
                    }
                }else if(strings[1].equalsIgnoreCase("reload")){
                    String pluginName = strings[0];
                    if(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName) != null) {
                        Essentials.getInstance().getServer().getPluginManager().disablePlugin(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName));
                        Essentials.getInstance().getServer().getPluginManager().enablePlugin(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName));
                        p.sendMessage(Essentials.prefix + "§aDas Plugin '" + pluginName + "' wurde neu geladen.");
                    } else {
                        p.sendMessage(Essentials.prefix + "§cDas Plugin '" + pluginName + "' ist nicht installiert.");
                    }
                }else if(strings[1].equalsIgnoreCase("info")) {
                    String pluginName = strings[0];
                    if(Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName) != null) {
                        p.sendMessage(Essentials.prefix + "§aPlugin: " + pluginName);
                        p.sendMessage(Essentials.prefix + "§aVersion: " + Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName).getDescription().getVersion());
                        p.sendMessage(Essentials.prefix + "§aAutor: " + Essentials.getInstance().getServer().getPluginManager().getPlugin(pluginName).getDescription().getAuthors());
                    } else {
                        p.sendMessage(Essentials.prefix + "§cDas Plugin '" + pluginName + "' ist nicht installiert.");
                    }
                }

            }else{
                p.sendMessage(Essentials.prefix + "§cBitte benutze: /pll <pluginname> <enable/disable/reload/info>");
            }
        }



        /*

        /pll <pluginname> <enable/disable/reload/info>


         */


        return false;
    }
}
