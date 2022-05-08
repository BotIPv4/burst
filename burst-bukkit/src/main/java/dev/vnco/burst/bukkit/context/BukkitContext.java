package dev.vnco.burst.bukkit.context;

import dev.vnco.burst.bukkit.completer.BukkitTabCompleter;
import dev.vnco.burst.shared.context.ChildrenContext;
import dev.vnco.burst.shared.context.Context;
import dev.vnco.burst.shared.context.InvokeContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class BukkitContext implements Context {

    private String[] aliases;
    private String permission, description;
    private boolean gameOnly;

    private Set<ChildrenContext> childrens;

    private BukkitTabCompleter completer;

    private InvokeContext invokeContext;

    @Override
    public void setPermission(Object permission) {
        this.permission = (String) permission;
    }

}
