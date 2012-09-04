package fr.aumgn.bukkitutils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a set of nested commands.
 *
 * All {@link Commands} subclasses which has this
 * annotation will prepend the given prefix to
 * their commands when registered. 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NestedCommands {

    String[] value();

    String defaultTo() default "";
}
