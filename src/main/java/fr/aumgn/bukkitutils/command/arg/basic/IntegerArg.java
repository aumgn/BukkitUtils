package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class IntegerArg extends CommandArg<Integer> {

    public static class Factory extends CommandArgFactory<IntegerArg> {

        @Override
        public IntegerArg createCommandArg(CommandsMessages messages, String string) {
            return new IntegerArg(messages, string);
        }
    }

    public IntegerArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Integer value() {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(messages.notAValidNumber(string));
        }
    }
}
