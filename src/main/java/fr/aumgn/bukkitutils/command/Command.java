package fr.aumgn.bukkitutils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * Name of the command (ie. preferably the real name and not an alias)
     */
    String name();

    /**
     * Minimum required number of arguments.
     */
    int min() default 0;

    /**
     * Maximum number of arguments.
     * Use -1 to set the number unlimited.
     */
    int max() default 0;

    /**
     * Accepted flags.
     */
    String flags() default "";

    /**
     * Accepted args flags.
     */
    String argsFlags() default "";
}
