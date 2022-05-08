package dev.vnco.burst.bukkit.context;

import dev.vnco.burst.bukkit.annotation.BukkitChildrenMeta;
import dev.vnco.burst.bukkit.annotation.BukkitCommandMeta;
import dev.vnco.burst.shared.CommandClass;
import dev.vnco.burst.shared.context.Context;
import dev.vnco.burst.shared.context.parser.ContextParser;
import dev.vnco.burst.shared.context.simple.SimpleInvokeContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;

public class BukkitContextParser implements ContextParser {

    @Override
    public void parse(Context context, Annotation annotation, Method method, CommandClass instance) {
        if (context instanceof BukkitContext && annotation instanceof BukkitCommandMeta) {
            BukkitContext bukkitContext = (BukkitContext) context;
            BukkitCommandMeta commandMeta = (BukkitCommandMeta) annotation;

            bukkitContext.setAliases(commandMeta.aliases());
            bukkitContext.setPermission(commandMeta.permission());
            bukkitContext.setDescription(commandMeta.description());
            bukkitContext.setGameOnly(commandMeta.gameOnly());

            bukkitContext.setChildrens(Collections.emptySet());
            bukkitContext.setInvokeContext(new SimpleInvokeContext(method, instance));
            return;
        }

        if (context instanceof BukkitChildrenContext && annotation instanceof BukkitChildrenMeta) {
            BukkitChildrenContext childrenContext = (BukkitChildrenContext) context;
            BukkitChildrenMeta childrenMeta = (BukkitChildrenMeta) annotation;

            childrenContext.setAliases(childrenMeta.aliases());
            childrenContext.setPermission(childrenMeta.permission());
            childrenContext.setGameOnly(childrenMeta.gameOnly());
            childrenContext.setParentLabel(childrenMeta.parentLabel());
            childrenContext.setInvokeContext(new SimpleInvokeContext(method, instance));
        }
    }
}
