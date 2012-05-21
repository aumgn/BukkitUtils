package fr.aumgn.bukkitutils.commands;

import java.util.Collections;
import java.util.Set;

import fr.aumgn.bukkitutils.command.CommandArgs;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandArgsUtil {

    private static final Messages local;
    private static final Set<Character> expectedFlags;

    static {
        local = new Messages();
        expectedFlags =
                Collections.<Character>emptySet();
    }

    public static CommandArgs parse(String... tokens) {
        return new CommandArgs(local, tokens,
                expectedFlags, tokens.length, tokens.length);
    }
}
