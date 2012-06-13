package fr.aumgn.bukkitutils.glob;

import java.util.List;

public interface GlobPattern<T> {

    boolean match(T obj);

    List<T> filter(T... objects);

    List<T> filter(List<T> objects);
}
