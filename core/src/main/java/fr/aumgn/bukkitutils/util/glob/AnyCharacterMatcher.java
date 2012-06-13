package fr.aumgn.bukkitutils.util.glob;

public class AnyCharacterMatcher implements GlobMatcher {

    @Override
    public int match(String str, int from) {
        return from + 1;
    }
}
