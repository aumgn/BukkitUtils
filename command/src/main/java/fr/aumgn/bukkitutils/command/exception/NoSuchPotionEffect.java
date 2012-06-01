package fr.aumgn.bukkitutils.command.exception;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class NoSuchPotionEffect extends CommandError {
	private static final long serialVersionUID = 6849291638184124428L;

    public NoSuchPotionEffect(Messages messages, String name) {
        super(String.format(messages.noSuchPotionEffect(), name));
    }
}
