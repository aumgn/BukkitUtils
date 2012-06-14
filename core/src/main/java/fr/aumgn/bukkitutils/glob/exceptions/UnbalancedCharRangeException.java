package fr.aumgn.bukkitutils.glob.exceptions;

public class UnbalancedCharRangeException extends GlobException {

    private static final long serialVersionUID = -630931242967117919L;

    private final String characterClass;

    public UnbalancedCharRangeException(String characterClass) {
        super("Unbalanced char range in : " + characterClass);
        this.characterClass = characterClass;
    }

    public String getCharClass() {
        return characterClass;
    }
}
