package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class DoubleArg extends CommandArg<Double> {

    public static class Factory extends CommandArgFactory<Double> {

        @Override
        public DoubleArg createCommandArg(Messages messages, String string) {
            return new DoubleArg(messages, string);
        }
    }

    public DoubleArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Double value() {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    String.format(messages.notAValidNumber(), string));
        }
    }
}
