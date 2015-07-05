package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class ShortArg extends AbstractCommandArg<Short> {

    private final CommandsMessages messages;

    public ShortArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public Short value() {
        try {
            return Short.parseShort(string);
        }
        catch (NumberFormatException exc) {
            throw new CommandUsageError(messages.notAValidNumber(string));
        }
    }
}
