package de.villigi.endervillageEssentials.utils;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarApi {

    public static void createBossBar(String title, float time, String color) {
        // Konvertiere die Farbe in BarColor
        BarColor barColor;
        try {
            barColor = BarColor.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            barColor = BarColor.WHITE; // Standardfarbe, falls ungültig
        }

        // Erstelle die BossBar
        BossBar bossBar = Bukkit.createBossBar(title, barColor, BarStyle.SOLID);

        // Setze die Fortschrittsanzeige (1.0 = 100%)
        bossBar.setProgress(1.0);

        // Zeige die BossBar allen Spielern an
        Bukkit.getOnlinePlayers().forEach(bossBar::addPlayer);

        // Reduziere die Fortschrittsanzeige basierend auf der Zeit
        new BukkitRunnable() {
            private float progress = 1.0f;

            @Override
            public void run() {
                progress -= 1.0f / (time * 20); // Zeit in Ticks (20 Ticks = 1 Sekunde)
                if (progress <= 0) {
                    bossBar.removeAll(); // Entferne die BossBar, wenn die Zeit abgelaufen ist
                    bossBar.setVisible(false);
                    cancel();
                } else {
                    bossBar.setProgress(progress);
                }
            }
        }.runTaskTimer(Essentials.getInstance(), 0L, 1L);
    }

    public static void createBossBar(Player p, String title, float time, String color) {
        // Konvertiere die Farbe in BarColor
        BarColor barColor;
        try {
            barColor = BarColor.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            barColor = BarColor.WHITE; // Standardfarbe, falls ungültig
        }

        // Erstelle die BossBar
        BossBar bossBar = Bukkit.createBossBar(title, barColor, BarStyle.SOLID);

        // Setze die Fortschrittsanzeige (1.0 = 100%)
        bossBar.setProgress(1.0);

        // Zeige die BossBar allen Spielern an
        bossBar.addPlayer(p);

        // Reduziere die Fortschrittsanzeige basierend auf der Zeit
        new BukkitRunnable() {
            private float progress = 1.0f;

            @Override
            public void run() {
                progress -= 1.0f / (time * 20); // Zeit in Ticks (20 Ticks = 1 Sekunde)
                if (progress <= 0) {
                    bossBar.removeAll(); // Entferne die BossBar, wenn die Zeit abgelaufen ist
                    bossBar.setVisible(false);
                    cancel();
                } else {
                    bossBar.setProgress(progress);
                }
            }
        }.runTaskTimer(Essentials.getInstance(), 0L, 1L);
    }
}