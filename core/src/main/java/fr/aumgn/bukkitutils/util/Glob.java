package fr.aumgn.bukkitutils.util;

import java.util.ArrayList;
import java.util.List;

import fr.aumgn.bukkitutils.util.glob.GlobPattern;

public class Glob {

    public static interface ToString<T> {
        String convert(T obj);
    }

    private Glob() {}

    public static boolean match(String pattern, String string) {
        return new GlobPattern(pattern).match(string);
    }

    public static <T> List<T> match(String patternString, T... objects) {
        GlobPattern pattern = new GlobPattern(patternString);
        List<T> result = new ArrayList<T>();

        for (T object : objects) {
            if (pattern.match(object.toString())) {
                result.add(object);
            }
        }

        return result;
    }

    public static <T> List<T> match(String patternString, List<T> objects) {
        GlobPattern pattern = new GlobPattern(patternString);
        List<T> result = new ArrayList<T>();

        for (T object : objects) {
            if (pattern.match(object.toString())) {
                result.add(object);
            }
        }

        return result;
    }

    public static <T> List<T> match(String patternString, List<T> objects, ToString<T> converter) {
        GlobPattern pattern = new GlobPattern(patternString);
        List<T> result = new ArrayList<T>();

        for (T object : objects) {
            if (pattern.match(converter.convert(object))) {
                result.add(object);
            }
        }

        return result;
    }

    public static <T> List<T> match(String patternString, T[] objects, ToString<T> converter) {
        GlobPattern pattern = new GlobPattern(patternString);
        List<T> result = new ArrayList<T>();

        for (T object : objects) {
            if (pattern.match(converter.convert(object))) {
                result.add(object);
            }
        }

        return result;
    }
}
