package fr.aumgn.bukkitutils.command.args;

import java.util.HashMap;
import java.util.Map;

import fr.aumgn.bukkitutils.command.messages.Messages;

public abstract class CommandArgsFactory {

    private static class Default extends CommandArgsFactory {

        @Override
        public CommandArgsInterface createCommandArgs(Messages messages, CommandArgsParser parser) {
            return new CommandArgs(messages, parser);
        }
    }

    private static final Map<Class<? extends CommandArgsInterface>, CommandArgsFactory> MAP;

    static {
        MAP = new HashMap<Class<? extends CommandArgsInterface>, CommandArgsFactory>();
        register(CommandArgs.class, new Default());
    }

    public static void register(Class<? extends CommandArgsInterface> ifaceClass,
            CommandArgsFactory factory) {
        MAP.put(ifaceClass, factory);
    }

    public static CommandArgsFactory get(Class<? extends CommandArgsInterface> argsClass) {
        return MAP.get(argsClass);
    }

    public abstract CommandArgsInterface createCommandArgs(Messages messages, CommandArgsParser parser);
}
