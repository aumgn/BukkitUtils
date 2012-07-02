package fr.aumgn.bukkitutils.glob.matchers;

public class AnyCharacterMatcher implements GlobMatcher {

    @Override
    public int match(String str, int from) {
        return from + 1;
    }
}
