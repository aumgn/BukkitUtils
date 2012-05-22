package fr.aumgn.bukkitutils.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandArgsBase implements Iterable<String> {

    protected Messages local;
    private Set<Character> flags;
    private String[] args;

    public CommandArgsBase(Messages local, String[] tokens, Set<Character> allowedFlags, int min, int max) {
        this.local = local;
        parse(tokens);
        validate(allowedFlags, min, max);
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

    private void validate(Set<Character> allowedFlags, int min, int max) {
        StringBuilder invalidFlags = new StringBuilder();
        for (char flag : flags) {
            if (!allowedFlags.contains(flag)) {
                invalidFlags.append(flag);
            }
        }

        if (invalidFlags.toString().length() > 0) {
            throw new CommandUsageError(
                    String.format(local.invalidFlag(), invalidFlags.toString()));
        }

        if (args.length < min) {
            throw new CommandUsageError(String.format(local.missingArguments(),
                    args.length, min));
        }
        if (max != -1 && args.length > max) {
            throw new CommandUsageError(String.format(local.tooManyArguments(),
                    args.length, max));
        }
    }

    public boolean hasFlags() {
        return !flags.isEmpty();
    }

    public boolean hasFlag(char character) {
        return flags.contains(character);
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

    @Override
    public Iterator<String> iterator() {
        return asList().iterator();
    }
}

