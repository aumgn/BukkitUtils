package fr.aumgn.bukkitutils.gconf;

public class GConfLoadRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1513384937300289127L;

    public GConfLoadRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public GConfLoadRuntimeException(String message) {
        super(message);
    }
}
