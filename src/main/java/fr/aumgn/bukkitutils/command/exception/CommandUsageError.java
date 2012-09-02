package fr.aumgn.bukkitutils.command.exception;

/**
 * Thrown when an error has been detected in the
 * usage of the command.
 */
public class CommandUsageError extends RuntimeException
        implements CommandException {
    private static final long serialVersionUID = -2455651120183829047L;

    public CommandUsageError() {
        super();
    }

    public CommandUsageError(String message) {
        super(message);
    }
}
