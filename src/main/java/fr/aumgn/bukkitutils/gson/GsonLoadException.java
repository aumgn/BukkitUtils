package fr.aumgn.bukkitutils.gson;

/**
 * Thrown when exception occurred when loading
 * or writing a resource.
 *
 */
public class GsonLoadException extends Exception {

    private static final long serialVersionUID = 8788570984149985804L;

    public GsonLoadException(Throwable throwable) {
        super(throwable);
    }

    public GsonLoadException(String message) {
        super(message);
    }
}
