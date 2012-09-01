package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.util.TimeUtil;
import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimeFormatException;
import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimePeriodException;

public class TimeArg extends CommandArg<Integer> {

    public static class Factory extends CommandArgFactory<TimeArg> {

        @Override
        public TimeArg createCommandArg(CommandsMessages messages,
                String string) {
            return new TimeArg(messages, string);
        }
    }

    public TimeArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Integer value() {
        try {
            return TimeUtil.parseTime(string);
        } catch (UnknownTimeFormatException exc) {
            throw new CommandError(
                    messages.unknownTimeFormat(exc.getTime()));
        } catch (UnknownTimePeriodException exc) {
            throw new CommandError(
                    messages.unknownTimePeriod(exc.getPeriod()));
        }
    }
}
