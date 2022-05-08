package dev.vnco.burst.shared.exception;

public class CommandException extends RuntimeException {

    public CommandException(String message, Object... objects) {
        super(String.format(message, objects));
    }

}
