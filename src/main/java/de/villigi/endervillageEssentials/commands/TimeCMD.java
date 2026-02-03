package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import de.villigi.endervillageEssentials.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TimeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if (p.hasPermission("essentials.time")) {
            if (strings.length == 1) {
                String time = strings[0].toLowerCase();
                switch (time) {
                    case "day":
                        p.getWorld().setTime(1000);
                        p.sendMessage(Essentials.prefix +"§aDie Zeit wurde auf Tag gesetzt.");
                        return true;
                    case "night":
                        p.getWorld().setTime(13000);
                        p.sendMessage(Essentials.prefix +"§aDie Zeit wurde auf Nacht gesetzt.");
                        return true;
                    case "noon":
                        p.getWorld().setTime(6000);
                        p.sendMessage(Essentials.prefix +"§aDie Zeit wurde auf Mittag gesetzt.");
                        return true;
                    case "midnight":
                        p.getWorld().setTime(18000);
                        p.sendMessage(Essentials.prefix +"§aDie Zeit wurde auf Mitternacht gesetzt.");
                        return true;

                    default:
                        if (Utils.isInteger(strings[0])) {
                            int customTime = Integer.parseInt(strings[0]);
                            if (customTime >= 0 && customTime <= 24000) {
                                p.getWorld().setTime(customTime);
                                p.sendMessage(Essentials.prefix +"§aDie Zeit wurde auf " + customTime + " gesetzt.");
                                return true;
                            } else {
                                p.sendMessage(Essentials.prefix +"§cUngültige Zeitangabe. Bitte gib eine Zahl zwischen 0 und 24000 an.");
                                return false;
                            }
                        } else {
                            p.sendMessage(Essentials.prefix +"§cUngültige Zeitangabe. Bitte benutze: /time <day/night/noon/midnight> oder eine Zahl zwischen 0 und 24000.");
                            return false;
                        }
                }
            } else {
                p.sendMessage(Essentials.prefix +"§cBitte benutze: /time <day/night/noon/midnight>");
                return false;
            }
        }else{
            p.sendMessage(Essentials.prefix + Essentials.perms);
        }


        return false;
    }
}
