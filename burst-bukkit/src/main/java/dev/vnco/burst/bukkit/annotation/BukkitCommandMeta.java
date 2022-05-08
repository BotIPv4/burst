package dev.vnco.burst.bukkit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BukkitCommandMeta {

    String[] aliases();

    String permission() default "";

    String description() default  "";

    boolean gameOnly() default false;

}
