package fr.aumgn.bukkitutils.glob.matchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.aumgn.bukkitutils.glob.exceptions.UnbalancedCharRangeException;

public class CharClass {

    private final Set<Character> characters;
    private final List<CharRange> ranges;

    public CharClass(String string) {
        HashSet<Character> charactersBuilder = new HashSet<Character>();
        ArrayList<CharRange> rangesBuilder = new ArrayList<CharRange>();

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
                Collections.unmodifiableList(rangesBuilder);
    }

    public boolean contains(char character) {
        if (characters.contains(character)) {
            return true;
        }

        for (CharRange range : ranges) {
            if (range.contains(character)) {
                return true;
            }
        }

        return false;
    }
}
