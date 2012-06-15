package fr.aumgn.bukkitutils.glob.matchers;

public class InvertedCharClass extends CharClass {

    public InvertedCharClass(String string) {
        super(string);
    }

    @Override
    public boolean contains(char character) {
        return !super.contains(character);
    }
}
