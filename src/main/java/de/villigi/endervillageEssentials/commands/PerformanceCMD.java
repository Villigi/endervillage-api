package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import de.villigi.endervillageEssentials.utils.ServerPerformance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PerformanceCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if (p.hasPermission("command.performace")) {
            if(strings.length == 0){
                p.sendMessage(Essentials.prefix + "§cVerwende: /performance <clearItems/ci | clearChat/cc>");
                return false;
            }

            if (strings[0].equalsIgnoreCase("clearItems")) {
                ServerPerformance.clearItems();
            } else if (strings[0].equalsIgnoreCase("ci")) {
                ServerPerformance.clearItems();
            } else if (strings[0].equalsIgnoreCase("clearChat")) {
                ServerPerformance.clearChat();
            } else if (strings[0].equalsIgnoreCase("cc")) {
                ServerPerformance.clearChat();
            }else{
                p.sendMessage(Essentials.prefix + "§cVerwende: /performance <clearItems/ci | clearChat/cc>");
            }
        }else{
            p.sendMessage(Essentials.prefix + Essentials.perms);
        }
        return false;
    }
}
