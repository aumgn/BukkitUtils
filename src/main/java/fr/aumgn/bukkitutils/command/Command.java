package fr.aumgn.bukkitutils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    public String name();

    public int min() default 0;

    public int max() default 0;

    public String flags() default "";
}
