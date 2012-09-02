package fr.aumgn.bukkitutils.command.arg;

public interface CommandArg<V> {

    /**
     * Returns the parsed value. This must not be called if
     * the corresponding argument is optional or bad things
     * may happen.
     */
    V value();

    /**
     * Returns the parsed value or the given default if the
     * argument is omitted.
     */
    V valueOr(V def);
}
