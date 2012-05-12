package fr.aumgn.bukkitutils.command.exception;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class InvalidMaterialAndDataFormat extends CommandError {
    private static final long serialVersionUID = 1L;

    public InvalidMaterialAndDataFormat(Messages messages, String given) {
        super(String.format(messages.invalidMaterialAndDataFormat(), given));
    }
}
