package fr.aumgn.bukkitutils.glob.matchers;

import org.apache.commons.lang.CharSet;

public class CharacterClassMatcher implements GlobMatcher {

    private final CharSet charSet;

    public CharacterClassMatcher(String string) {
        charSet = CharSet.getInstance(string);
    }

    public CharSet getCharSet() {
        return charSet;
    }

    @Override
    public int match(String str, int from) {
        if (from >= str.length()) {
            return -1;
        }

        char character = str.charAt(from);
        if (charSet.contains(character)) {
            return from + 1;
        }

        return -1;
    }
}
