package dev.vnco.burst.bukkit;

import dev.vnco.burst.shared.CommandSource;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Getter
public class BukkitCommandSource extends CommandSource<CommandSender> {

    public BukkitCommandSource(CommandSender handle) {
        super(handle);
    }

    @Override
    public void sendMessage(String... message) {
        for (String s : message) {
            handle.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

    @Override
    public boolean hasPermission(Object permission) {
        return handle.hasPermission((String) permission);
    }

}
