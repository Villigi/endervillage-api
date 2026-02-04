package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import de.villigi.endervillageEssentials.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ItemRenameCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Player p = (Player) commandSender;
        if(!p.hasPermission("command.item.rename")) {
            p.sendMessage(Essentials.prefix + Essentials.perms);
            return false;
        }
        String displayName = "";
        for(int i = 0; i < strings.length; i++) {
            displayName = displayName + strings[i] + " ";
        }
        if (p.getItemInHand() != null && p.getItemInHand().getItemMeta() != null) {
            ItemMeta meta = p.getItemInHand().getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
            p.getItemInHand().setItemMeta(meta);
            p.sendMessage(Essentials.prefix + "§7Neuer Displayname: " + ChatColor.translateAlternateColorCodes('&', displayName));
        } else {
            p.sendMessage(Essentials.prefix + "§cDu hältst kein gültiges Item in der Hand!");
        }
        return false;
    }
}
