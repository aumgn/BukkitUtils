package fr.aumgn.bukkitutils.glob.matchers;

public class CharacterClassMatcher implements GlobMatcher {

    private final CharClass charactersClass;

    public CharacterClassMatcher(String string) {
        if (string.length() > 1 && string.charAt(0) == '^') {
            this.charactersClass = new InvertedCharClass(string.substring(1));
        } else {
            this.charactersClass = new CharClass(string);
        }
    }

    public CharClass getCharactersClass() {
        return charactersClass;
    }

    @Override
    public int match(String str, int from) {
        if (from >= str.length()) {
            return -1;
        }

        char character = str.charAt(from);
        if (charactersClass.contains(character)) {
            return from + 1;
        }

        return -1;
    }
}
