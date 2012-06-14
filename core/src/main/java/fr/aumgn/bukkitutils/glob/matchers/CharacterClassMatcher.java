package fr.aumgn.bukkitutils.glob.matchers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import fr.aumgn.bukkitutils.glob.exceptions.UnbalancedCharRangeException;

public class CharacterClassMatcher implements GlobMatcher {

    private final Set<Character> characters;
    private final Set<CharRange> ranges;

    public CharacterClassMatcher(String string) {
        HashSet<Character> charactersBuilder = new HashSet<Character>();
        HashSet<CharRange> rangesBuilder = new HashSet<CharRange>();

        if (string.length() > 0) {
            char[] chars = string.toCharArray();
            if (chars[0] == '-') {
                throw new UnbalancedCharRangeException(string);
            }

            for (int i = 0; i < chars.length; i++) {
                int j = i + 1;
                if (j < chars.length && chars[j] == '-') {
                    j++;
                    if (j >= chars.length) {
                        throw new UnbalancedCharRangeException(string);
                    }
                    rangesBuilder.add(new CharRange(chars[i], chars[j]));
                    i = j;
                } else {
                    charactersBuilder.add(chars[i]);
                }
            }
        }

        this.characters =
                Collections.unmodifiableSet(charactersBuilder);
        this.ranges =
                Collections.unmodifiableSet(rangesBuilder);
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public Set<CharRange> getRanges() {
        return ranges;
    }

    @Override
    public int match(String str, int from) {
        if (from >= str.length()) {
            return -1;
        }

        char character = str.charAt(from);
        if (characters.contains(character)) {
            return from + 1;
        }

        for (CharRange range : ranges) {
            if (range.contains(character)) {
                return from + 1;
            }
        }

        return -1;
    }
}
