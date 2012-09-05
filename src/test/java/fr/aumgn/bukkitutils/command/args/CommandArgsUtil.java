package fr.aumgn.bukkitutils.command.args;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import fr.aumgn.bukkitutils.command.CommandsLocalization;
import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.args.CommandArgsParser;

public class CommandArgsUtil {

    private static final CommandsMessages MESSAGES;
    private static final Set<Character> NO_FLAGS;

    static {
        CommandsLocalization localization =
                new CommandsLocalization(Locale.US);
        MESSAGES = localization.get("commands");
        NO_FLAGS = Collections.<Character>emptySet();
    }

    public static CommandArgs parse(String... tokens) {
        return parse(NO_FLAGS, tokens.length, tokens.length, tokens);
    }

    public static CommandArgs parse(int min, int max, String... tokens) {
        return parse(NO_FLAGS, min, max, tokens);
    }

    public static CommandArgs parse(Set<Character> flags, String... tokens) {
        return parse(flags, 0, -1, tokens);
    }

    public static CommandArgs parse(Set<Character> flags,
            int min, int max, String... tokens) {
        return parse(flags, NO_FLAGS, min, max, tokens);
    }

    public static CommandArgs parseWithArgsFlags(Set<Character> argsFlags,
            String... tokens) {
        return parseWithArgsFlags(argsFlags, 0, -1, tokens);
    }

    public static CommandArgs parseWithArgsFlags(Set<Character> argsFlags,
            int min, int max, String... tokens) {
        return parse(NO_FLAGS, argsFlags, min, max, tokens);
    }

    public static CommandArgs parse(Set<Character> flags,
            Set<Character> argsFlags, String... tokens) {
        return parse(flags, argsFlags, 0, -1, tokens);
    }

    public static CommandArgs parse(Set<Character> flags,
            Set<Character> argsFlags, int min, int max, String... tokens) {
        CommandArgsParser parser = new CommandArgsParser(MESSAGES, tokens);
        parser.validate(true, flags, argsFlags, min, max);
        return new CommandArgs(MESSAGES, parser);
    }
}
