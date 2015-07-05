package fr.aumgn.bukkitutils.glob.matchers;

import fr.aumgn.bukkitutils.glob.exceptions.UnbalancedSquareBracketException;

import java.util.ArrayList;
import java.util.List;

public class GlobParser {

    private GlobParser() {
    }

    public static List<GlobMatcher> parse(String pattern, int start) {
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
                            throw new UnbalancedSquareBracketException(pattern);
                        }
                    }
                    matchers.add(new CharacterClassMatcher(pattern.substring(
                            startIndex, i)));
                    break;
                }

                default: {
                    int startIndex = i;
                    while (i < pattern.length() && pattern.charAt(i) != '*'
                            && pattern.charAt(i) != '?'
                            && pattern.charAt(i) != '[') {
                        i++;
                        if (i >= pattern.length()) {
                            break;
                        }
                    }
                    matchers.add(new TextMatcher(
                            pattern.substring(startIndex, i)));
                    if (i >= pattern.length()) {
                        break;
                    }
                    i--;
                }
            }
        }
        return matchers;
    }
}
