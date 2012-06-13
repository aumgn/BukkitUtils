package fr.aumgn.bukkitutils.glob.patterns;

import java.util.Locale;

public class StringCIGlobPattern extends StringGlobPattern {

    public StringCIGlobPattern(String pattern) {
        super(pattern.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public boolean match(String str) {
        return super.match(str.toLowerCase());
    }
}
