package fr.aumgn.bukkitutils.command.exception;

public class CommandError extends RuntimeException implements CommandException {
    private static final long serialVersionUID = -7087606268182929669L;

    public CommandError(String message) {
        super(message);
    }
}
