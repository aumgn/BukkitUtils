package fr.aumgn.bukkitutils.util.glob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobPattern {

    static List<GlobMatcher> parse(String pattern, int start) {
        ArrayList<GlobMatcher> matchers = new ArrayList<GlobMatcher>();
        for (int i = start; i < pattern.length(); i++) {
            char character = pattern.charAt(i);
            switch (character) {
            case '*': {
                matchers.add(new WildcardMatcher(pattern, i + 1));
                return matchers;
            }
            case '?': {
                matchers.add(new AnyCharacterMatcher());
                break;
            }
            case '[': {
                i++;
                int startIndex = i;
                while (i < pattern.length() && pattern.charAt(i) != ']') {
                    i++;
                    if (i >= pattern.length()) {
                        throw new RuntimeException("");
                    }
                }
                matchers.add(new CharacterClassMatcher(pattern.substring(
                        startIndex, i)));
                break;
            }
            default: {
                int startIndex = i;
                while (i < pattern.length() && pattern.charAt(i) != '*'
                        && pattern.charAt(i) != '?' && pattern.charAt(i) != '[') {
                    i++;
                    if (i >= pattern.length()) {
                        break;
                    }
                }
                matchers.add(new TextMatcher(pattern.substring(startIndex, i)));
                if (i >= pattern.length()) {
                    break;
                }
                i--;
            }
            }
        }
        return matchers;
    }

    private final List<GlobMatcher> matchers;

    public GlobPattern(String pattern) {
        this.matchers = parse(pattern, 0);
    }

    public List<GlobMatcher> getMatchers() {
        return Collections.unmodifiableList(matchers);
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
}
