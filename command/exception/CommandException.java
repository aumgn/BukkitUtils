package fr.aumgn.bukkitutils.command.exception;

public class CommandException extends RuntimeException {
    private static final long serialVersionUID = 1760382462081928711L;

    public CommandException(Throwable throwable) {
        super(throwable);
    }
}
