package fr.aumgn.bukkitutils.glob.matchers;

public class TextMatcher implements GlobMatcher {

    private final String text;

    public TextMatcher(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int match(String str, int from) {
        if (str.startsWith(text, from)) {
            return from + text.length();
        } else {
            return -1;
        }
    }
}
