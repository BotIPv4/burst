package dev.vnco.burst.bukkit.topic;

import org.bukkit.help.HelpTopic;
import org.bukkit.help.IndexHelpTopic;

import java.util.Collection;

public class BukkitCommandIndexHelpTopic extends IndexHelpTopic {

    public BukkitCommandIndexHelpTopic(String name, Collection<HelpTopic> topics) {
        super(name, "All commands for " + name + ".", null, topics);
    }

}
