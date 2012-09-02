package fr.aumgn.bukkitutils.glob;

import java.util.Locale;

import com.google.common.base.Function;

import fr.aumgn.bukkitutils.glob.patterns.GenericGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringCIGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.StringGlobPattern;
import fr.aumgn.bukkitutils.glob.patterns.WildcardGlobPattern;

/**
 * GlobPattern builder class.
 */
public class Glob {

    private static class CaseInsensitiveToString<T>
            implements Function<T, String> {

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

    /**
     * Defines that the glob  match the entire token.
     * Default.
     */
    public Glob exact() {
        this.partialStart = false;
        this.partialEnd = false;
        return this;
    }

    /**
     * Defines that the glob match the token
     * from the start.
     */
    public Glob fromStart() {
        this.partialEnd = true;
        return this;
    }

    /**
     * Defines that the glob match the token partially.
     */
    public Glob partial() {
        this.partialStart = true;
        this.partialEnd = true;
        return this;
    }

    /**
     * Defines that the glob is case sensitive.
     * Default.
     */
    public Glob caseSensitive() {
        caseInsensitive = false;
        return this;
    }

    /**
     * Alias for {@link #caseSensitive()}.
     */
    public Glob cs() {
        return caseSensitive();
    }

    /**
     * Defines that the glob is case insensitive.
     */
    public Glob caseInsensitive() {
        caseInsensitive = true;
        return this;
    }

    /**
     * Alias for {@link #caseInsensitive()}.
     */
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

    /**
     * Construct the {@link GlobPattern}.
     */
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

    /**
     * Construct the {@link GlobPattern}.
     */
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
