package de.villigi.endervillageEssentials;

import de.villigi.endervillageEssentials.commands.*;
import de.villigi.endervillageEssentials.database.DatabaseManager;
import de.villigi.endervillageEssentials.utils.MessageApi;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Essentials extends JavaPlugin {

    private static Essentials instance;
    private DatabaseManager databaseManager;


    public static String prefix = null;
    public static String perms = null;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();


        databaseManager = new DatabaseManager();
        databaseManager.loadFiles();
        try {
            databaseManager.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        loadListeners();
        loadMessages();


        getLogger().info("Endervillage Essentials Plugin wurde aktiviert!");
    }

    public void registerCommands() {
        getCommand("gamemode").setExecutor(new GamemodeCMD());
        getCommand("time").setExecutor(new TimeCMD());
        getCommand("Pluginloader").setExecutor(new PluginLoaderCMD());
        getCommand("fly").setExecutor(new FlyCMD());
        getCommand("messages").setExecutor(new MessagesCMD());
        getCommand("help").setExecutor(new HelpCMD());
        getCommand("performance").setExecutor(new PerformanceCMD());
    }

    public void loadListeners() {
        getServer().getPluginManager().registerEvents(new MessagesCMD(), this);
    }

    public void loadMessages() {
        new MessageApi("prefix").addMessage("§8[§aEndervillage§8] §7");
        new MessageApi("no_permission").addMessage("§cDazu hast du keine Rechte!");
        prefix = new MessageApi("prefix").getMessage();
        perms = new MessageApi("no_permission").getMessage();



    }


    @Override
    public void onDisable() {

    }

    public static Essentials getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
