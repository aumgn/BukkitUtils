package fr.aumgn.bukkitutils.command.exception;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class NoSuchMaterial extends CommandError {
    private static final long serialVersionUID = 6849291638184124428L;

    public NoSuchMaterial(Messages messages, String name) {
        super(String.format(messages.noSuchMaterial(), name));
    }
}
