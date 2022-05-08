package dev.vnco.burst.bukkit;

import dev.vnco.burst.shared.CommandArgs;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@Getter
public class BukkitCommandArgs extends CommandArgs {

    private final BukkitCommandSource source;

    public BukkitCommandArgs(BukkitCommandSource source, String[] array) {
        super(source, array);
        this.source = source;
    }

    public void sendMessage(String... message) {
        getSource().sendMessage(message);
    }

    public Player getPlayer() {
        return (Player) getSender();
    }

    public boolean isConsole() {
        return getSender() instanceof ConsoleCommandSender;
    }

    public boolean isPlayer() {
        return getSender() instanceof Player;
    }

    public String getArg(int i) {
        return getArray()[i - 1];
    }

    public int getLength() {
        return getArray().length;
    }

    public CommandSender getSender() {
        return source.getHandle();
    }
}
