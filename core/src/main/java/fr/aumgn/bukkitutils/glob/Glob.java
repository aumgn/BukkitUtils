package fr.aumgn.bukkitutils.glob;

import java.util.Locale;

import fr.aumgn.bukkitutils.glob.patterns.GenericGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringCIGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.WildcardGlobPattern;

public class Glob {

    public static interface ToString<T> {
        String convert(T obj);
    }

    private static class CaseInsensitiveToString<T> implements ToString<T> {

        private final ToString<T> toString;

        public CaseInsensitiveToString(ToString<T> toString) {
            this.toString = toString;
        }

        @Override
        public String convert(T obj) {
            return toString.convert(obj).toLowerCase(Locale.ENGLISH);
        }
    }

    private final String rawPattern;
    private boolean partialStart;
    private boolean partialEnd;
    private boolean caseInsensitive;

    public Glob(String pattern) {
        this.rawPattern = pattern;
        this.partialStart = false;
        this.partialEnd = false;
        this.caseInsensitive = false;
    }

    public Glob exact() {
        this.partialStart = false;
        this.partialEnd = false;
        return this;
    }

    public Glob start() {
        this.partialEnd = true;
        return this;
    }

    public Glob partial() {
        this.partialStart = true;
        this.partialEnd = true;
        return this;
    }

    public Glob caseSensitive() {
        caseInsensitive = false;
        return this;
    }

    public Glob cs() {
        return caseSensitive();
    }

    public Glob caseInsensitive() {
        caseInsensitive = true;
        return this;
    }

    public Glob ci() {
        return caseInsensitive();
    }

    private String getPattern() {
        String pattern = rawPattern;

        if (partialStart && pattern.charAt(0) != '*') {
            pattern = "*" + pattern;
        }

        if (partialEnd && pattern.charAt(pattern.length() - 1) != '*') {
            pattern = pattern + "*";
        }

        if (caseInsensitive) {
            pattern = pattern.toLowerCase(Locale.ENGLISH);
        }
        return pattern;
    }

    public GlobPattern<String> build() {
        if (rawPattern == "*") {
            return new WildcardGlobPattern<String>();
        }

        if (caseInsensitive) {
            return new StringCIGlobPattern(getPattern());
        } else {
            return new StringGlobPattern(getPattern());
        }
    }

    public <T> GlobPattern<T> build(ToString<T> toString) {
        if (rawPattern == "*") {
            return new WildcardGlobPattern<T>();
        }

        ToString<T> caseAwareToString = toString; 
        if (caseInsensitive) {
            caseAwareToString = new CaseInsensitiveToString<T>(toString);
        }

        return new GenericGlobPattern<T>(getPattern(), caseAwareToString);
    }
}
