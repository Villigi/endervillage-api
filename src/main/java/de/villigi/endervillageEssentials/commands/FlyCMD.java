package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("essentials.command.fly")) {
            if (strings.length == 0) {
                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage("§aFliegen wurde deaktiviert.");
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage("§aFliegen wurde aktiviert.");
                }
            } else if (strings.length == 1) {
                Player target = p.getServer().getPlayerExact(strings[0]);
                if (target != null) {
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        target.sendMessage(Essentials.prefix +"§aFliegen wurde deaktiviert.");
                        p.sendMessage(Essentials.prefix +"§aDu hast das Fliegen für " + target.getName() + " deaktiviert.");
                    } else {
                        target.setAllowFlight(true);
                        target.sendMessage(Essentials.prefix +"§aFliegen wurde aktiviert.");
                        p.sendMessage(Essentials.prefix +"§aDu hast das Fliegen für " + target.getName() + " aktiviert.");
                    }
                } else {
                    p.sendMessage(Essentials.prefix +"§cDer Spieler §e" + strings[0] + " §cist nicht online.");
                }
            } else {
                p.sendMessage("§cBitte benutze: /fly <Spieler>");
            }
        }else{
            p.sendMessage(Essentials.prefix + Essentials.perms);
            return false;
        }

        return false;
    }
}
