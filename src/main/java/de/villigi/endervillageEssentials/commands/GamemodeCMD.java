package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if (p.hasPermission("essentials.gamemode")) {
            if (strings.length == 1) {
                String gamemode = strings[0].toLowerCase();
                switch (gamemode) {
                    case "0", "survival", "s":
                        p.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        p.sendMessage(Essentials.prefix + "§aDu hast deinen Spielmodus auf §7Survival §ageändert.");
                        return true;
                    case "1", "creative", "c":
                        p.setGameMode(org.bukkit.GameMode.CREATIVE);
                        p.sendMessage(Essentials.prefix + "§aDu hast deinen Spielmodus auf §7Kreativ §ageändert.");
                        return true;
                    case "2", "adventure", "a":
                        p.setGameMode(org.bukkit.GameMode.ADVENTURE);
                        p.sendMessage(Essentials.prefix + "§aDu hast deinen Spielmodus auf §7Abenteuer §ageändert.");
                        return true;
                    case "3", "spectator", "sp":
                        p.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        p.sendMessage(Essentials.prefix + "§aDu hast deinen Spielmodus auf §7Zuschauer §ageändert.");
                        return true;
                    default:
                        p.sendMessage(Essentials.prefix + "§cUngültiger Spielmodus. Bitte benutze: /gamemode <0/1/2/3/survival/creative/adventure/spectator>");
                        return false;
                }
            } else {
                p.sendMessage(Essentials.prefix + "§cBitte benutze: /gamemode <0/1/2/3/survival/creative/adventure/spectator>");
                return false;
            }
        }else{
            p.sendMessage(Essentials.prefix + Essentials.perms);
        }
        return false;
    }
}