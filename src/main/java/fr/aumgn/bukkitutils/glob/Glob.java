package fr.aumgn.bukkitutils.glob;

import java.util.Locale;

import com.google.common.base.Function;

import fr.aumgn.bukkitutils.glob.patterns.GenericGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringCIGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.WildcardGlobPattern;

public class Glob {

    private static class CaseInsensitiveToString<T> implements Function<T, String> {

        private final Function<T, String> toString;

        public CaseInsensitiveToString(Function<T, String> toString) {
            this.toString = toString;
        }

        @Override
        public String apply(T obj) {
            return toString.apply(obj).toLowerCase(Locale.ENGLISH);
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

    public <T> GlobPattern<T> build(Function<T, String> toString) {
        if (rawPattern == "*") {
            return new WildcardGlobPattern<T>();
        }

        Function<T, String> caseAwareToString = toString; 
        if (caseInsensitive) {
            caseAwareToString = new CaseInsensitiveToString<T>(toString);
        }

        return new GenericGlobPattern<T>(getPattern(), caseAwareToString);
    }
}
