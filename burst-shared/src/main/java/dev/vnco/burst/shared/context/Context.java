package dev.vnco.burst.shared.context;

import dev.vnco.burst.shared.CommandSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface Context extends ContextBase {

    String getDescription();

    void setDescription(String description);

    default boolean hasDescription() {
        return getDescription() != null && !getDescription().equals("");
    }

    Set<ChildrenContext> getChildrens();

    void setChildrens(Set<ChildrenContext> childrens);

    default List<String> getChildrensNames(CommandSource<?> source) {

        if (!getChildrens().isEmpty()) {
            List<String> list = new ArrayList<>();

            getChildrens().stream().filter(context -> !context.hasPermission() || source.hasPermission(context.getPermission()))
                    .forEach(context -> Collections.addAll(list, context.getAliases()));

            return list;
        }

        return Collections.emptyList();
    }

    default ChildrenContext findChildren(String name) {
        return getChildrens().stream().filter(context -> {

            for (String aliases : context.getAliases()) {
                return aliases.equalsIgnoreCase(name);
            }

            return false;
        }).findFirst().orElse(null);
    }

}
