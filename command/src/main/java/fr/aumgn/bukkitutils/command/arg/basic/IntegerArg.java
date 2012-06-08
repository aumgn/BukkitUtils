package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class IntegerArg extends CommandArg<Integer> {

    public static class Factory extends CommandArgFactory<Integer> {

        @Override
        public IntegerArg createCommandArg(Messages messages, String string) {
            return new IntegerArg(messages, string);
        }
    }

    public IntegerArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Integer value() {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    String.format(messages.notAValidNumber(), string));
        }
    }
}
