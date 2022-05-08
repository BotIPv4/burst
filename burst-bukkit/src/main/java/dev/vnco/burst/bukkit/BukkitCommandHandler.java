package dev.vnco.burst.bukkit;

import dev.vnco.burst.bukkit.annotation.BukkitChildrenMeta;
import dev.vnco.burst.bukkit.annotation.BukkitCommandMeta;
import dev.vnco.burst.bukkit.annotation.BukkitCompleterMeta;
import dev.vnco.burst.bukkit.completer.BukkitTabCompleter;
import dev.vnco.burst.bukkit.context.BukkitChildrenContext;
import dev.vnco.burst.bukkit.context.BukkitContext;
import dev.vnco.burst.bukkit.context.BukkitContextParser;
import dev.vnco.burst.shared.CommandClass;
import dev.vnco.burst.shared.CommandHandler;
import dev.vnco.burst.shared.context.parser.ContextParser;
import dev.vnco.burst.shared.exception.CommandException;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Getter
public class BukkitCommandHandler implements CommandHandler {

    private final BukkitCommandRegistry registry;
    private final BukkitContextParser contextParser;

    private final Set<BukkitCommand> commands;
    private final Set<CommandClass> commandClasses;

    @Setter
    private String noPermissionMessage, argumentNotFoundMessage, gameOnlyMessage;

    public BukkitCommandHandler() {
        registry = new BukkitCommandRegistry();
        contextParser = new BukkitContextParser();
        commands = new HashSet<>();
        commandClasses = new HashSet<>();
    }

    @Override
    public void register(CommandClass commandClass) {
        Class<? extends CommandClass> clazz = commandClass.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);

            BukkitCommandMeta commandMeta = method.getAnnotation(BukkitCommandMeta.class);

            if (commandMeta == null) {
                continue;
            }

            Class<?>[] types = method.getParameterTypes();

            if (types.length < 1 || types[0] != BukkitCommandArgs.class) {
                throw new CommandException(

                        "Parameter BukkitCommandArgs not found at '%s' Method (%s)",
                        method.getName(), clazz.getName()

                );
            }

            BukkitContext context = new BukkitContext();

            getParser().parse(context, commandMeta, method, commandClass);

            BukkitCompleterMeta completerMeta = method.getAnnotation(BukkitCompleterMeta.class);

            method.setAccessible(false);

            if (completerMeta != null) {
                context.setCompleter(new BukkitTabCompleter(completerMeta));
            }

            commands.add(new BukkitCommand(this, context));
            commandClasses.add(commandClass);
        }
    }

    @Override
    public void unregisterAll() {
        for (BukkitCommand command : commands) {
            registry.unregister(command);
        }

        commands.clear();
        commandClasses.clear();
    }

    @Override
    public void applyFor(Object object) {
        registry.register((String) object, commands);
        registerChildrens();
    }

    @Override
    public void registerChildrens() {
        for (CommandClass commandClass : commandClasses) {
            for (Method method : commandClass.getClass().getDeclaredMethods()) {
                method.setAccessible(true);

                BukkitChildrenMeta childrenMeta = method.getAnnotation(BukkitChildrenMeta.class);

                if (childrenMeta == null) {
                    continue;
                }

                for (BukkitCommand command : commands) {
                    if (!childrenMeta.parentLabel().equalsIgnoreCase(command.getLabel())) {
                        continue;
                    }

                    BukkitChildrenContext childrenContext = new BukkitChildrenContext();

                    getParser().parse(childrenContext, childrenMeta, method, commandClass);

                    command.getContext().getChildrens().add(childrenContext);
                }

                method.setAccessible(false);
            }
        }
    }

    @Override
    public ContextParser getParser() {
        return contextParser;
    }
}
