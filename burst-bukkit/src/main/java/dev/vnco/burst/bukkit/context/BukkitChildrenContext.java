package dev.vnco.burst.bukkit.context;

import dev.vnco.burst.shared.context.ChildrenContext;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BukkitChildrenContext extends BukkitContext implements ChildrenContext {

    private String description, parentLabel;

}
