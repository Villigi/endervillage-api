package de.villigi.endervillageEssentials.commands;

import de.villigi.endervillageEssentials.Essentials;
import de.villigi.endervillageEssentials.utils.MessageApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;

public class MessagesCMD implements CommandExecutor, Listener {

    private String prefix = Essentials.prefix;


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(prefix + "§cNur Spieler können diesen Befehl ausführen.");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("Command.Messages")) {
            player.sendMessage(prefix + Essentials.perms);
            return true;
        }

        // Beispiel-Nachrichten (Placeholder und Nachricht)
        Map<String, String> messages = null;
        try {
            messages = MessageApi.getMessages();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        openMessagesInventory(player, messages, 1);
        return true;
    }

    private void openMessagesInventory(Player player, Map<String, String> messages, int page) {
        int inventorySize = 54; // Kisten-Inventar (6 Reihen)
        Inventory inventory = Bukkit.createInventory(null, inventorySize, "Nachrichten - Seite " + page);

        int itemsPerPage = inventorySize - 9; // 45 Plätze für Bücher
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, messages.size());

        // Nachrichten als Bücher hinzufügen
        List<Map.Entry<String, String>> messageList = new ArrayList<>(messages.entrySet());
        for (int i = startIndex; i < endIndex; i++) {
            Map.Entry<String, String> entry = messageList.get(i);
            ItemStack book = createBookItem(entry.getKey(), entry.getValue());
            inventory.setItem(i - startIndex, book);
        }

        // Navigation hinzufügen
        if (page > 1) {
            inventory.setItem(45, createNavigationItem("§aZurück", Material.ARROW));
        }
        if (endIndex < messages.size()) {
            inventory.setItem(53, createNavigationItem("§aWeiter", Material.ARROW));
        }

        player.openInventory(inventory);
    }

    private ItemStack createBookItem(String title, String message) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = book.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6" + title);
            meta.setLore(Collections.singletonList("§7" + message));
            book.setItemMeta(meta);
        }
        return book;
    }

    private ItemStack createNavigationItem(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        String title = event.getView().getTitle();
        if (!title.startsWith("Nachrichten - Seite")) return;

        event.setCancelled(true); // Verhindert das Herausnehmen von Items

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() != Material.ARROW) return;

        String displayName = clickedItem.getItemMeta().getDisplayName();

        // Extrahiere die Seitennummer aus dem Titel
        int currentPage;
        try {
            currentPage = Integer.parseInt(title.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            player.sendMessage("§cFehler: Ungültige Seitennummer.");
            return;
        }

        // Beispiel-Nachrichten (Placeholder und Nachricht)
        Map<String, String> messages = null;
        try {
            messages = MessageApi.getMessages();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (displayName.contains("Zurück")) {
            openMessagesInventory(player, messages, currentPage - 1);
        } else if (displayName.contains("Weiter")) {
            openMessagesInventory(player, messages, currentPage + 1);
        }
    }
}