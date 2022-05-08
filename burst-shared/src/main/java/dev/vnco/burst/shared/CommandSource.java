package dev.vnco.burst.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public abstract class CommandSource<S> {

    protected final S handle;

    public abstract void sendMessage(String... message);

    public abstract boolean hasPermission(Object permission);

}
