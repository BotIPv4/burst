package dev.vnco.burst.bukkit.topic;

import dev.vnco.burst.bukkit.BukkitCommand;
import dev.vnco.burst.bukkit.context.BukkitContext;
import org.bukkit.command.CommandSender;
import org.bukkit.help.GenericCommandHelpTopic;

public class BukkitCommandTopic extends GenericCommandHelpTopic {

    private final BukkitContext context;

    public BukkitCommandTopic(BukkitCommand bukkitCommand) {
        super(bukkitCommand);
        this.context = bukkitCommand.getContext();
    }

    @Override
    public boolean canSee(CommandSender sender) {
        if (!context.hasPermission()) {
            return true;
        }

        return sender.hasPermission(context.getPermission());
    }

}
