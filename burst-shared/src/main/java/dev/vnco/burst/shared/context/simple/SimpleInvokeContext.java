package dev.vnco.burst.shared.context.simple;

import dev.vnco.burst.shared.CommandClass;
import dev.vnco.burst.shared.context.InvokeContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter @AllArgsConstructor
public class SimpleInvokeContext implements InvokeContext {

    private Method method;
    private CommandClass instance;

}
