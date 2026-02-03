package de.villigi.endervillageEssentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class PluginLoader {

    public static boolean loadPlugin(String pluginName) {
        File pluginFile = new File("plugins", pluginName + ".jar");
        if (!pluginFile.exists()) {
            Bukkit.getLogger().severe("Plugin " + pluginName + " konnte nicht gefunden werden.");
            return false;
        }

        try {
            PluginManager pluginManager = Bukkit.getPluginManager();
            Plugin plugin = pluginManager.loadPlugin(pluginFile);
            if (plugin != null) {
                plugin.onLoad();
                pluginManager.enablePlugin(plugin);
                Bukkit.getLogger().info("Plugin " + pluginName + " wurde erfolgreich geladen.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean unloadPlugin(String pluginName) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            Bukkit.getLogger().severe("Plugin " + pluginName + " ist nicht geladen.");
            return false;
        }

        try {
            PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.disablePlugin(plugin);

            Field pluginsField = pluginManager.getClass().getDeclaredField("plugins");
            pluginsField.setAccessible(true);
            Field lookupNamesField = pluginManager.getClass().getDeclaredField("lookupNames");
            lookupNamesField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, Plugin> plugins = (Map<String, Plugin>) pluginsField.get(pluginManager);
            @SuppressWarnings("unchecked")
            Map<String, Plugin> lookupNames = (Map<String, Plugin>) lookupNamesField.get(pluginManager);

            plugins.remove(plugin.getName());
            lookupNames.remove(plugin.getName());

            Bukkit.getLogger().info("Plugin " + pluginName + " wurde erfolgreich entladen.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean reloadPlugin(String pluginName) {
        if (unloadPlugin(pluginName)) {
            return loadPlugin(pluginName);
        }
        return false;
    }
}