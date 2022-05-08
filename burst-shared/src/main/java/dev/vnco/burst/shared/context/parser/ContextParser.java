package dev.vnco.burst.shared.context.parser;

import dev.vnco.burst.shared.CommandClass;
import dev.vnco.burst.shared.context.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@FunctionalInterface
public interface ContextParser {

    void parse(Context context, Annotation annotation, Method method, CommandClass instance);

}
