package fr.aumgn.bukkitutils.glob.patterns;

import fr.aumgn.bukkitutils.glob.GlobPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardGlobPattern<T> implements GlobPattern<T> {

    @Override
    public boolean match(Object obj) {
        return true;
    }

    @Override
    public List<T> filter(T... objects) {
        return Arrays.asList(objects);
    }

    @Override
    public List<T> filter(List<T> objects) {
        return new ArrayList<T>(objects);
    }
}
