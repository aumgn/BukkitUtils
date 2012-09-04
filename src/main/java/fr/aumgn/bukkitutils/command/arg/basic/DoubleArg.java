package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class DoubleArg extends AbstractCommandArg<Double> {

    private final CommandsMessages messages;

    public DoubleArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
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
