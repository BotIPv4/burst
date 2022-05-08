package dev.vnco.burst.shared.context;

import dev.vnco.burst.shared.CommandArgs;
import dev.vnco.burst.shared.CommandClass;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.Objects;

public interface InvokeContext {

    @Nullable Method getMethod();

    CommandClass getInstance();

    default void invoke(CommandArgs args) {
        try {
            Objects.requireNonNull(getMethod(), "Method cannot be null");
            getMethod().invoke(getInstance(), args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
