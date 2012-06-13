package fr.aumgn.bukkitutils.glob.patterns;

import fr.aumgn.bukkitutils.glob.Glob.ToString;

public class GenericGlobPattern<T> extends AbstractGlobPattern<T> {

    private final ToString<T> toString;

    public GenericGlobPattern(String pattern, ToString<T> toString) {
        super(pattern);
        this.toString = toString;
    }

    public boolean match(T obj) {
        return match(toString.convert(obj));
    }
}
