package fr.aumgn.bukkitutils.command.exception;

/**
 * Thrown when an error occurred while executing a command.
 */
public class CommandError extends RuntimeException implements CommandException {
    private static final long serialVersionUID = -7087606268182929669L;

    public CommandError(String message) {
        super(message);
    }
}
