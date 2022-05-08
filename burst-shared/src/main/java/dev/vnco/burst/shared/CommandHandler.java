package dev.vnco.burst.shared;

import dev.vnco.burst.shared.context.parser.ContextParser;

public interface CommandHandler {

    void register(CommandClass commandClass);

    default CommandHandler append(CommandClass commandClass) {
        register(commandClass);
        return this;
    }

    void unregisterAll();

    void applyFor(Object object);

    void registerChildrens();

    void setNoPermissionMessage(String noPermissionMessage);

    String getNoPermissionMessage();

    void setArgumentNotFoundMessage(String argumentNotFoundMessage);

    String getArgumentNotFoundMessage();

    ContextParser getParser();

}
