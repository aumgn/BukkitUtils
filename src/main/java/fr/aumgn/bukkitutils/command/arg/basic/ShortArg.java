package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class ShortArg extends CommandArg<Short> {

    public static class Factory extends CommandArgFactory<Short> {

        @Override
        public ShortArg createCommandArg(CommandsMessages messages, String string) {
            return new ShortArg(messages, string);
        }
    }

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
