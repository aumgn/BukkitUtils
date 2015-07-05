package fr.aumgn.bukkitutils.glob.patterns;

import fr.aumgn.bukkitutils.glob.GlobPattern;
import fr.aumgn.bukkitutils.glob.matchers.GlobMatcher;
import fr.aumgn.bukkitutils.glob.matchers.GlobParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGlobPattern<T> implements GlobPattern<T> {

    private final List<GlobMatcher> matchers;

    public AbstractGlobPattern(String pattern) {
        this.matchers = Collections.unmodifiableList(
                GlobParser.parse(pattern, 0));
    }

    public List<GlobMatcher> getMatchers() {
        return matchers;
    }

    public boolean match(String str) {
        if (matchers.isEmpty()) {
            return str.isEmpty();
        }

        int index = 0;
        for (GlobMatcher matcher : matchers) {
            index = matcher.match(str, index);
            if (index < 0) {
                return false;
            }
        }

        return index == str.length();
    }

    @Override
    public List<T> filter(T... objects) {
        List<T> result = new ArrayList<T>();

        for (T obj : objects) {
            if (match(obj)) {
                result.add(obj);
            }
        }

        return result;
    }

    @Override
    public List<T> filter(List<T> objects) {
        List<T> result = new ArrayList<T>();

        for (T obj : objects) {
            if (match(obj)) {
                result.add(obj);
            }
        }

        return result;
    }
}
