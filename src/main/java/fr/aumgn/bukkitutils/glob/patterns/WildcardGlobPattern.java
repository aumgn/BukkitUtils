package fr.aumgn.bukkitutils.glob.patterns;

import com.google.common.collect.ImmutableList;
import fr.aumgn.bukkitutils.glob.GlobPattern;

public class WildcardGlobPattern<T> implements GlobPattern<T> {

    @Override
    public boolean match(Object obj) {
        return true;
    }

    @Override
    public ImmutableList<T> filter(T... objects) {
        return ImmutableList.copyOf(objects);
    }

    @Override
    public <E extends T> ImmutableList<E> filter(Iterable<E> objects) {
        return ImmutableList.copyOf(objects);
    }
}
