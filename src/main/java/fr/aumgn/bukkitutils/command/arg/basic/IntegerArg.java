package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class IntegerArg extends AbstractCommandArg<Integer> {

    private final CommandsMessages messages;

    public IntegerArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public Integer value() {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException exc) {
            throw new CommandUsageError(messages.notAValidNumber(string));
        }
    }
}
