package fr.aumgn.bukkitutils.command.exception;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class NoSuchColor extends CommandError {

    private static final long serialVersionUID = 2534023126371443961L;

    public NoSuchColor(Messages messages, String name) {
        super(String.format(messages.noSuchColor(), name));
    }
}
