package dev.vnco.burst.bukkit;

import dev.vnco.burst.bukkit.topic.BukkitCommandIndexHelpTopic;
import dev.vnco.burst.bukkit.topic.BukkitCommandTopic;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;

public class BukkitCommandRegistry {

    private final HelpMap helpMap = Bukkit.getHelpMap();

    public void register(String name, BukkitCommand command) {
        Collection<HelpTopic> topics = new HashSet<>();

        topics.add(new BukkitCommandTopic(command));

        getCommandMap().register(name, command);

        helpMap.addTopic(new BukkitCommandIndexHelpTopic(name, topics));
    }

    public void register(String name, Collection<BukkitCommand> commands) {
        for (BukkitCommand command : commands) {
            register(name, command);
        }
    }

    public void unregister(BukkitCommand bukkitCommand) {
        bukkitCommand.unregister(getCommandMap());
    }

    private CommandMap getCommandMap() {
        try {
            SimplePluginManager pluginManager = (SimplePluginManager) Bukkit.getPluginManager();

            Field field = pluginManager.getClass().getDeclaredField("commandMap");
            field.setAccessible(true);

            return (CommandMap) field.get(pluginManager);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
