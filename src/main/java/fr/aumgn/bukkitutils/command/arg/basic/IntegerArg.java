package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class IntegerArg extends AbstractCommandArg<Integer> {

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
