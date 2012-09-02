package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class ShortArg extends AbstractCommandArg<Short> {

    public ShortArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Short value() {
        try {
            return Short.parseShort(string);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(messages.notAValidNumber(string));
        }
    }
}
