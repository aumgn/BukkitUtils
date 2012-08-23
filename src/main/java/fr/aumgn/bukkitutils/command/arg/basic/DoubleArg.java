package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class DoubleArg extends CommandArg<Double> {

    public static class Factory extends CommandArgFactory<Double> {

        @Override
        public DoubleArg createCommandArg(CommandsMessages messages, String string) {
            return new DoubleArg(messages, string);
        }
    }

    public DoubleArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Double value() {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(messages.notAValidNumber(string));
        }
    }
}
