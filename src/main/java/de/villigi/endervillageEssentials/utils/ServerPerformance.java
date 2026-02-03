package de.villigi.endervillageEssentials.utils;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class ServerPerformance {

    public static void clearItems() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(Essentials.prefix + "§cAlle auf dem Boden liegenden Items werden in 30 Sekunden gelöscht!");
            BossBarApi.createBossBar("§cItems werden in 30 Sekunden gelöscht!", 30, "RED");
            all.playSound(all.getLocation(), "entity.experience_orb.pickup",1.0f,1.0f);
        }

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Endervillage-Essentials"), () -> {
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item) {
                        entity.remove(); // Entfernt das Item
                    }
                }
            }
            Bukkit.broadcastMessage("§cAlle auf dem Boden liegenden Items wurden gelöscht!");
        }, 30 * 20L); // 30 Sekunden (20 Ticks = 1 Sekunde)
    }

    public static void clearChat() {
        for (int i = 0; i < 100; i++) {
            Bukkit.broadcastMessage(" ");
        }
        Bukkit.broadcastMessage(Essentials.prefix + "§7------------ §4Chat Clear §7------------ ");
        Bukkit.broadcastMessage(Essentials.prefix + " ");
        Bukkit.broadcastMessage(Essentials.prefix + " ");
        Bukkit.broadcastMessage(Essentials.prefix + "§cDer Chat wurde von einem Administrator geleert!");
        Bukkit.broadcastMessage(Essentials.prefix + " ");
        Bukkit.broadcastMessage(Essentials.prefix + " ");
        Bukkit.broadcastMessage(Essentials.prefix + "§7---------------------------------------- ");

    }
}