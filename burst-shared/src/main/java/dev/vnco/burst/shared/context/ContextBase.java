package dev.vnco.burst.shared.context;

import org.jetbrains.annotations.Nullable;

public interface ContextBase {

    String[] getAliases();

    void setAliases(String[] aliases);

    @Nullable
    Object getPermission();

    void setPermission(Object permission);

    default boolean hasPermission() {
        return getPermission() != null;
    }

    InvokeContext getInvokeContext();

    void setInvokeContext(InvokeContext invokeContext);

}
