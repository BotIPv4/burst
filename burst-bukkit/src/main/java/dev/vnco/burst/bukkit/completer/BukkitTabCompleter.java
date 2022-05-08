package dev.vnco.burst.bukkit.completer;

import dev.vnco.burst.bukkit.annotation.BukkitCompleterMeta;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class BukkitTabCompleter implements TabCompleter {

    private final BukkitCompleterMeta completerMeta;

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] array) {
        if (array.length != completerMeta.length()) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();

        for (String complete : completerMeta.complete()) {
            String permission = completerMeta.permission();

            if (permission == null || sender.hasPermission(permission)) {
                list.add(complete);
            }
        }

        return list;
    }

}
