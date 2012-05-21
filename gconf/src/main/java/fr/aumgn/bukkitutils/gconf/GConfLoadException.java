package fr.aumgn.bukkitutils.gconf;

public class GConfLoadException extends Exception {

    private static final long serialVersionUID = 8788570984149985804L;

    public GConfLoadException(Throwable throwable) {
        super(throwable);
    }

    public GConfLoadException(String message) {
        super(message);
    }
}
