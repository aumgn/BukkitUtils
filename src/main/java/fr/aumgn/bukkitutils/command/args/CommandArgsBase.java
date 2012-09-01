package fr.aumgn.bukkitutils.command.args;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.CommandListArg;
import fr.aumgn.bukkitutils.command.arg.basic.EnumArg;

public class CommandArgsBase {

    protected final CommandsMessages messages;
    private final Set<Character> flags;
    private final Map<Character, String> argsFlags;
    private final String[] args;

    public CommandArgsBase(CommandsMessages messages, CommandArgsParser parser) {
        this.messages = messages;
        this.flags = parser.getFlags();
        this.argsFlags = parser.getArgsFlags();
        this.args = parser.getArgs();
    }

    public boolean hasIndex(int index) {
        return index < args.length;
    }

    public boolean hasFlags() {
        return !flags.isEmpty() || !argsFlags.isEmpty();
    }

    public boolean hasFlag(char character) {
        return flags.contains(character);
    }

    public boolean hasArgFlag(char character) {
        return argsFlags.containsKey(character);
    }

    public Iterable<Character> flags() {
        return flags;
    }

    public int length() {
        return args.length;
    }

    public String get(int index) {
        return args[index];
    }

    public String get(int index, String def) {
        if (hasIndex(index)) {
            return get(index);
        }

        return def;
    }

    public String get(char flag) {
        return argsFlags.get(flag);
    }

    public String get(char flag, String def) {
        if (hasArgFlag(flag)) {
            return get(flag);
        }

        return def;
    }

    private String getOrNull(int index) {
        return get(index, (String) null);
    }

    private String getOrNull(char flag) {
        return get(flag, (String) null);
    }

    public <T extends CommandArg<?>> T get(int index, CommandArgFactory<T> factory) {
        return factory.createCommandArg(messages, getOrNull(index));
    }

    public <T extends CommandArg<?>> T get(int index, Class<T> klass) {
        CommandArgFactory<T> factory = CommandArgFactory.get(klass);
        return get(index, factory);
    }

    public <T extends CommandArg<?>> T get(char flag, CommandArgFactory<T> factory) {
        return factory.createCommandArg(messages, getOrNull(flag));
    }

    public <T extends CommandArg<?>> T get(char flag, Class<T> klass) {
        CommandArgFactory<T> factory = CommandArgFactory.get(klass);
        return get(flag, factory);
    }

    public <T extends Enum<T>> EnumArg<T> getEnum(int index,
            Class<T> enumClass) {
        return new EnumArg<T>(messages, getOrNull(index), enumClass);
    }

    public <T extends Enum<T>> EnumArg<T> getEnum(char flag,
            Class<T> enumClass) {
        return new EnumArg<T>(messages, getOrNull(flag), enumClass);
    }

    public <T> CommandListArg<T> getList(int index,
            CommandArgFactory<? extends CommandArg<T>> factory) {
        return new CommandListArg<T>(messages, factory, getOrNull(index));
    }

    public <T> CommandListArg<T> getList(int index,
            String separator, CommandArgFactory<? extends CommandArg<T>> factory) {
        return new CommandListArg<T>(messages, separator,
                factory, getOrNull(index));
    }

    public <T> CommandListArg<T> getList(int index,
            Class<? extends CommandArg<T>> klass) {
        CommandArgFactory<? extends CommandArg<T>> factory = CommandArgFactory.get(klass);
        return getList(index, factory);
    }

    public <T> CommandListArg<T> getList(int index, String separator,
            Class<? extends CommandArg<T>> klass) {
        CommandArgFactory<? extends CommandArg<T>> factory = CommandArgFactory.get(klass);
        return getList(index, separator, factory);
    }

    public <U> CommandListArg<U> getList(char flag,
            CommandArgFactory<CommandArg<U>> factory) {
        return new CommandListArg<U>(messages, factory, getOrNull(flag));
    }

    public <T> CommandListArg<T> getList(char flag, String separator,
            CommandArgFactory<CommandArg<T>> factory) {
        return new CommandListArg<T>(messages, separator,
                factory, getOrNull(flag));
    }

    public <T> CommandListArg<T> getList(char flag,
            Class<CommandArg<T>> klass) {
        CommandArgFactory<? extends CommandArg<T>> factory = CommandArgFactory.get(klass);
        return getList(flag, factory);
    }

    public <T> CommandListArg<T> getList(char flag, String separator,
            Class<CommandArg<T>> klass) {
        CommandArgFactory<? extends CommandArg<T>> factory = CommandArgFactory.get(klass);
        return getList(flag, separator, factory);
    }

    public String get(int index, int rawEndIndex) {
        int endIndex;
        if (rawEndIndex > -1) {
            endIndex = rawEndIndex;
        } else {
            endIndex = args.length - 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < endIndex; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        builder.append(args[endIndex]);
        return builder.toString();
    }

    public String get(int index, int endIndex, String def) {
        if (hasIndex(index) && hasIndex(endIndex)) {
            return get(index, endIndex);
        }

        return def;
    }

    public List<String> asList() {
        return Arrays.asList(args);
    }

    public List<String> asList(int index) {
        return asList(index, args.length - 1);
    }

    public List<String> asList(int index, int endIndex) {
        int startIndex = Math.max(0, index);
        int actualEndIndex = Math.min(args.length, endIndex + 1);
        return asList().subList(startIndex, actualEndIndex);
    }

    public Iterator<String> iterator() {
        return asList().iterator();
    }
}
