package fr.aumgn.bukkitutils.gson;

public class GsonLoadRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1513384937300289127L;

    public GsonLoadRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public GsonLoadRuntimeException(String message) {
        super(message);
    }
}
