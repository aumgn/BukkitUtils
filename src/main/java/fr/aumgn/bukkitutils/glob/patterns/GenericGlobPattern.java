package fr.aumgn.bukkitutils.glob.patterns;

import com.google.common.base.Function;

public class GenericGlobPattern<T> extends AbstractGlobPattern<T> {

    private final Function<T, String> toString;

    public GenericGlobPattern(String pattern, Function<T, String> toString) {
        super(pattern);
        this.toString = toString;
    }

    public boolean match(T obj) {
        return match(toString.apply(obj));
    }
}
