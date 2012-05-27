package fr.aumgn.bukkitutils.command.args;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandArgsParser {

    private Messages messages;
    private String[] args;
    private Set<Character> flags;

    public CommandArgsParser(Messages messages, String[] tokens) {
        this.messages = messages;
        parse(tokens);
    }

    private void parse(String[] tokens) {
        flags = new HashSet<Character>();
        ArrayList<String> argsList = new ArrayList<String>();
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            if (token.charAt(0) == '-' && token.length() > 1
                    && Character.isLetter(token.charAt(1))) {
                parseFlags(token.substring(1));
            } else {
                argsList.add(token);
            }
        }

        args = argsList.toArray(new String[argsList.size()]);
    }

    private void parseFlags(String flagsString) {
        for (char flag : flagsString.toCharArray()) {
            flags.add(flag);
        }
    }

    public void validate(Set<Character> allowedFlags, int min, int max) {
        StringBuilder invalidFlags = new StringBuilder();
        for (char flag : flags) {
            if (!allowedFlags.contains(flag)) {
                invalidFlags.append(flag);
            }
        }

        if (invalidFlags.toString().length() > 0) {
            throw new CommandUsageError(
                    String.format(messages.invalidFlag(), invalidFlags.toString()));
        }

        if (args.length < min) {
            throw new CommandUsageError(String.format(messages.missingArguments(),
                    args.length, min));
        }
        if (max != -1 && args.length > max) {
            throw new CommandUsageError(String.format(messages.tooManyArguments(),
                    args.length, max));
        }
    }

    public Set<Character> getFlags() {
        return flags;
    }

    public String[] getArgs() {
        return args;
    }
}
