package fr.aumgn.bukkitutils.glob;

import java.util.List;

/**
 * GlobPattern interface.
 * <p/>
 * To build a glob pattern you should use
 * the builder class Glob.
 * <p/>
 * A GlobPattern is defined from a string
 * which can contains special matching sequence:
 * <ul>
 * <li>
 * "*" (wildcard):
 * Matches any number of any characters.
 * </li>
 * <li>
 * "?":
 * Matches any character.
 * </li>
 * <li>
 * "[abc]" (characters class):
 * Matches all given characters
 * </li>
 * <li>
 * "[^abc] (inverted characters class):
 * Matches all characters except the given one
 * </li>
 * </ul>
 */
public interface GlobPattern<T> {

    /**
     * Checks if this pattern matches the given obj.
     */
    boolean match(T obj);

    /**
     * Filters the given array of objects using this
     * pattern.
     */
    List<T> filter(T... objects);

    /**
     * Filters the given list of objects using this
     * pattern.
     */
    List<T> filter(List<T> objects);
}
