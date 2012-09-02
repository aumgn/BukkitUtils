package fr.aumgn.bukkitutils.glob.exceptions;

/**
 * Thrown when a glob contains an unbalanced
 * opening square bracket.
 */
public class UnbalancedSquareBracketException extends GlobException {

    private static final long serialVersionUID = -4559595738685591466L;

    private final String glob;

    public UnbalancedSquareBracketException(String glob) {
        super("Unbalanced square bracket in : " + glob);
        this.glob = glob;
    }

    public String getGlob() {
        return glob;
    }
}
