package fr.aumgn.bukkitutils.command.args;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.basic.EnumArg;
import fr.aumgn.bukkitutils.command.arg.basic.ListArg;

import java.util.*;

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

    private boolean hasIndex(int index) {
        return index < args.length;
    }

    /**
     * Checks if the command has flags either
     * boolean flag or argument flag.
     */
    public boolean hasFlags() {
        return !flags.isEmpty() || !argsFlags.isEmpty();
    }

    /**
     * Checks if the commands has the given boolean
     * flag.
     */
    public boolean hasFlag(char character) {
        return flags.contains(character);
    }

    /**
     * Checks if the commands has the given argument
     * flag.
     */
    public boolean hasArgFlag(char character) {
        return argsFlags.containsKey(character);
    }

    /**
     * Returns an iterable over all boolean flags.
     */
    public Iterable<Character> flags() {
        return flags;
    }

    /**
     * Returns the number of argument.
     */
    public int length() {
        return args.length;
    }

    /**
     * Get the argument at the given position.
     */
    public String get(int index) {
        return args[index];
    }

    /**
     * Get the argument at the given position or
     * the default if argument is omitted.
     */
    public String get(int index, String def) {
        if (hasIndex(index)) {
            return get(index);
        }

        return def;
    }

    /**
     * Get the given argument flag value.
     */
    public String get(char flag) {
        return argsFlags.get(flag);
    }

    /**
     * Get the given argument flag value or
     * the default if omitted.
     */
    public String get(char flag, String def) {
        if (hasArgFlag(flag)) {
            return get(flag);
        }

        return def;
    }

    protected String getOrNull(int index) {
        return get(index, (String) null);
    }

    protected String getOrNull(char flag) {
        return get(flag, (String) null);
    }

    /**
     * Get the {@link CommandArg} corresponding to
     * the given factory for the given index.
     */
    public <T extends CommandArg<?>> T get(int index,
                                           CommandArgFactory<T> factory) {
        return factory.createCommandArg(getOrNull(index));
    }

    /**
     * Get the {@link CommandArg} corresponding to
     * the given factory for the given argument flag.
     */
    public <T extends CommandArg<?>> T get(char flag,
                                           CommandArgFactory<T> factory) {
        return factory.createCommandArg(getOrNull(flag));
    }

    /**
     * Get the {@link EnumArg} value corresponding to
     * the given enum class for the given index.
     */
    public <T extends Enum<T>> EnumArg<T> get(int index, Class<T> enumClass) {
        return new EnumArg<T>(messages, getOrNull(index), enumClass);
    }

    /**
     * Get the {@link EnumArg} value corresponding to
     * the given enum class for the given flag.
     */
    public <T extends Enum<T>> EnumArg<T> get(char flag, Class<T> enumClass) {
        return new EnumArg<T>(messages, getOrNull(flag), enumClass);
    }

    /**
     * Get the {@link ListArg} wrapping the {@link CommandArg}
     * corresponding to the given factory for the given index.
     */
    public <V, T extends CommandArg<V>> ListArg<V, T> getList(int index,
                                                              CommandArgFactory<T> factory) {
        return new ListArg<V, T>(",", factory, getOrNull(index));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link CommandArg}
     * corresponding to the given factory for the given index
     * using the given separator.
     */
    public <V, T extends CommandArg<V>> ListArg<V, T> getList(int index,
                                                              CommandArgFactory<T> factory, String separator) {
        return new ListArg<V, T>(separator, factory, getOrNull(index));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link CommandArg}
     * corresponding to the given factory for the given flag.
     */
    public <V, T extends CommandArg<V>> ListArg<V, T> getList(char flag,
                                                              CommandArgFactory<T> factory) {
        return new ListArg<V, T>(",", factory, getOrNull(flag));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link CommandArg}
     * corresponding to the given factory for the given flag
     * using the given separator.
     */
    public <V, T extends CommandArg<V>> ListArg<V, T> getList(char flag,
                                                              CommandArgFactory<T> factory, String separator) {
        return new ListArg<V, T>(separator, factory, getOrNull(flag));
    }

    private <T extends Enum<T>> CommandArgFactory<EnumArg<T>>
    enumArgFactory(final Class<T> enumClass) {
        return new CommandArgFactory<EnumArg<T>>() {
            @Override
            public EnumArg<T> createCommandArg(String string) {
                return new EnumArg<T>(messages, string, enumClass);
            }
        };
    }

    /**
     * Get the {@link ListArg} wrapping the {@link EnumArg}
     * corresponding to the given enum class for the given index.
     */
    public <T extends Enum<T>> ListArg<T, EnumArg<T>> getList(int index,
                                                              Class<T> klass) {
        return new ListArg<T, EnumArg<T>>(",", enumArgFactory(klass),
                getOrNull(index));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link EnumArg}
     * corresponding to the given enum class for the given index
     * using the given separator.
     */
    public <T extends Enum<T>> ListArg<T, EnumArg<T>> getList(int index,
                                                              final Class<T> klass, String separator) {
        return new ListArg<T, EnumArg<T>>(separator, enumArgFactory(klass),
                getOrNull(index));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link EnumArg}
     * corresponding to the given enum class for the given flag.
     */
    public <T extends Enum<T>> ListArg<T, EnumArg<T>> getList(char flag,
                                                              final Class<T> klass) {
        return new ListArg<T, EnumArg<T>>(",", enumArgFactory(klass),
                getOrNull(flag));
    }

    /**
     * Get the {@link ListArg} wrapping the {@link EnumArg}
     * corresponding to the given enum class for the given flag
     * using the given separator.
     */
    public <T extends Enum<T>> ListArg<T, EnumArg<T>> getList(char flag,
                                                              final Class<T> klass, String separator) {
        return new ListArg<T, EnumArg<T>>(separator, enumArgFactory(klass),
                getOrNull(flag));
    }

    /**
     * Get the string containing all arguments from index
     * to endIndex separated by a whitespace.
     */
    public String get(int index, int rawEndIndex) {
        int endIndex;
        if (rawEndIndex > -1) {
            endIndex = rawEndIndex;
        }
        else {
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

    /**
     * Get the string containing all arguments from index
     * to endIndex separated by a whitespace or the given default.
     */
    public String get(int index, int endIndex, String def) {
        if (hasIndex(index) && hasIndex(endIndex)) {
            return get(index, endIndex);
        }

        return def;
    }

    /**
     * Returns all arguments as a list.
     */
    public List<String> asList() {
        return Arrays.asList(args);
    }

    /**
     * Returns all arguments starting from index as a list.
     */
    public List<String> asList(int index) {
        return asList(index, args.length - 1);
    }

    /**
     * Returns all arguments from index to endIndex
     * as a list.
     */
    public List<String> asList(int index, int endIndex) {
        int startIndex = Math.max(0, index);
        int actualEndIndex = Math.min(args.length, endIndex + 1);
        return asList().subList(startIndex, actualEndIndex);
    }

    /**
     * Iterator over all arguments
     * (does not include argument flags).
     */
    public Iterator<String> iterator() {
        return asList().iterator();
    }
}
