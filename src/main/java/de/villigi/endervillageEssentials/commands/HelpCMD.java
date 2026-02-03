package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if(p.hasPermission("command.Help")) {
            p.sendMessage(Essentials.prefix + "§7------------> §6§lHelp Menü §7<------------ ");
            p.sendMessage(Essentials.prefix + "§e/gamemode §7- §fSetze deinen Gamemode (Survival, Creative, Adventure, Spectator)");
            p.sendMessage(Essentials.prefix + "§e/time §7- §fSetze die Zeit (Day, Night, Noon, Midnight)");
            p.sendMessage(Essentials.prefix + "§e/fly §7- §fAktiviere/Deaktiviere den Flugmodus");
            p.sendMessage(Essentials.prefix + "§e/messages §7- §fÖffne das Nachrichten Menü");
            p.sendMessage(Essentials.prefix + "§e/performance (/perf) §7- §fServer Performance Befehle (Clear Items, Clear Chat)");
            p.sendMessage(Essentials.prefix + "§7------------------------------------------- ");
            p.playSound(p.getLocation(), "entity.player.levelup",1.0f,1.0f);
        }else{
            p.sendMessage(Essentials.prefix + Essentials.perms);
        }

        return false;
    }
}
