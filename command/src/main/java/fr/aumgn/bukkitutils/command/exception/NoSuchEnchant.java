package fr.aumgn.bukkitutils.command.exception;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class NoSuchEnchant extends CommandError {
	
	private static final long serialVersionUID = -4832133406864970323L;

	public NoSuchEnchant(Messages messages, String name) {
		super(String.format(messages.noSuchEnchant(), name));
	}

}
