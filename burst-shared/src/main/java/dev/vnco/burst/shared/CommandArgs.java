package dev.vnco.burst.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class CommandArgs {

    private final CommandSource<?> source;
    private final String[] array;

}
