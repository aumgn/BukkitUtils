package fr.aumgn.bukkitutils.glob.matchers;

import java.util.Collections;
import java.util.List;


public class WildcardMatcher implements GlobMatcher {

    private final List<GlobMatcher> matchers;

    public WildcardMatcher(String pattern, int i) {
        this.matchers = Collections.unmodifiableList(
                GlobParser.parse(pattern, i));
    }

    public List<GlobMatcher> getMatchers() {
        return matchers;
    }

    @Override
    public int match(String str, int from) {
        if (matchers.isEmpty()) {
            return str.length();
        }

        wildcard:
        for (int i = from; i < str.length(); i++) {
            int index = i;
            for (GlobMatcher matcher : matchers) {
                index = matcher.match(str, index);
                if (index < 0) {
                    continue wildcard;
                }
            }
            if (index == str.length()) {
                return index;
            }
        }

        return -1;
    }
}
