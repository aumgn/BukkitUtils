package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.util.TimeUtil;
import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimeFormatException;
import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimePeriodException;

public class TimeArg extends AbstractCommandArg<Integer> {

    private final CommandsMessages messages;

    public TimeArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public Integer value() {
        try {
            return TimeUtil.parseTime(string);
        }
        catch (UnknownTimeFormatException exc) {
            throw new CommandError(
                    messages.unknownTimeFormat(exc.getTime()));
        }
        catch (UnknownTimePeriodException exc) {
            throw new CommandError(
                    messages.unknownTimePeriod(exc.getPeriod()));
        }
    }
}
