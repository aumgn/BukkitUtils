package fr.aumgn.bukkitutils.command.arg;

import java.util.List;

public interface MatchingArg<V> {

    /**
     * Returns all values which match the argument.
     * This should not be called is argument is optional.
     */
    List<V> match();

    /**
     * Returns all values which match the argument or
     * a list containing the given default if argument
     * is omitted.
     */
    List<V> matchOr(V def);

    /**
     * Returns all values which match the argument or
     * the given default list if the argument is omitted.
     */
    List<V> matchOr(List<V> def);
}
