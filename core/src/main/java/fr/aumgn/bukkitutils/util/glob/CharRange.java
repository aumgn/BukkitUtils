package fr.aumgn.bukkitutils.util.glob;

public class CharRange {

    private final char from;
    private final char to;

    public CharRange(char char1, char char2) {
        if (char1 > char2) {
            this.from = char2;
            this.to = char1;
        } else {
            this.from = char1;
            this.to = char2;
        }
    }

    public char getFrom() {
        return from;
    }

    public char getTo() {
        return to;
    }

    public boolean contains(char character) {
        return character >= from && character <= to;
    }
}
