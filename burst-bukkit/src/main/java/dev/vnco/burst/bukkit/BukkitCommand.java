package dev.vnco.burst.bukkit;

import dev.vnco.burst.bukkit.completer.BukkitTabCompleter;
import dev.vnco.burst.bukkit.context.BukkitContext;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class BukkitCommand extends Command {

    private final BukkitCommandHandler commandHandler;
    private final BukkitContext context;

    public BukkitCommand(BukkitCommandHandler commandHandler, BukkitContext context) {
        super(context.getAliases()[0]);
        this.context = context;
        this.commandHandler = commandHandler;

        setAliases(Arrays.asList(context.getAliases()));

        if (context.hasDescription()) {
            setDescription(context.getDescription());
        }
    }

    @Override
    public boolean execute(CommandSender sender, String name, String[] array) {
        new BukkitCommandExecutor(commandHandler, context).execute(new BukkitCommandSource(sender), array);
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String label, String[] array) {
        BukkitTabCompleter completer = context.getCompleter();

        if (completer != null) {
            return completer.onTabComplete(sender, this, label, array);
        }

        if (array.length == 1) {
            return context.getChildrensNames(new BukkitCommandSource(sender));
        }

        return Collections.emptyList();
    }
}
