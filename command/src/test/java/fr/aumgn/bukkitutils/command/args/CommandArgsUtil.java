package fr.aumgn.bukkitutils.command.args;

import java.util.Collections;
import java.util.Set;

import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.args.CommandArgsParser;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandArgsUtil {

    private static final Messages MESSAGES;
    private static final Set<Character> NO_FLAG;

    static {
        MESSAGES = new Messages();
        NO_FLAG = Collections.<Character>emptySet();
    }

    public static CommandArgs parse(String... tokens) {
        return parse(NO_FLAG, tokens.length, tokens.length, tokens);
    }

    public static CommandArgs parse(int min, int max, String... tokens) {
        return parse(NO_FLAG, min, max, tokens);
    }

    public static CommandArgs parse(Set<Character> flags, String... tokens) {
        return parse(flags, 0, -1, tokens);
    }

    public static CommandArgs parse(Set<Character> flags, int min, int max, String... tokens) {
        CommandArgsParser parser = new CommandArgsParser(MESSAGES, tokens);
        parser.validate(flags, min, max);
        return new CommandArgs(MESSAGES, parser);
    }
}
