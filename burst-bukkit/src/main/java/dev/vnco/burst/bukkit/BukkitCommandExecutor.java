package dev.vnco.burst.bukkit;

import dev.vnco.burst.bukkit.context.BukkitContext;
import dev.vnco.burst.shared.CommandExecutor;
import dev.vnco.burst.shared.CommandSource;
import dev.vnco.burst.shared.context.Context;
import dev.vnco.burst.shared.context.ContextBase;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitCommandExecutor extends CommandExecutor {

    private final BukkitCommandHandler commandHandler;

    public BukkitCommandExecutor(BukkitCommandHandler commandHandler, Context context) {
        super(context);
        this.commandHandler = commandHandler;
    }

    @Override
    public String getArgumentNotFoundMessage() {
        return commandHandler.getArgumentNotFoundMessage();
    }

    @Override
    public String getNoPermissionsMessage() {
        return commandHandler.getNoPermissionMessage();
    }

    @Override
    protected boolean checkIfSourceHasPermission(ContextBase contextBase, CommandSource<?> source) {
        BukkitContext bukkitContext = (BukkitContext) contextBase;

        if (bukkitContext.isGameOnly()) {
            if (source.getHandle() instanceof ConsoleCommandSender) {
                source.sendMessage(commandHandler.getGameOnlyMessage());
                return true;
            }
        }

        return super.checkIfSourceHasPermission(contextBase, source);
    }

}
