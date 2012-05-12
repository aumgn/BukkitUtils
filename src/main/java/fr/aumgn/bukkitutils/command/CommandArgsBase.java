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

    public CommandArgsBase(Messages local, String[] tokens, Set<Character> allowedFlag, int min, int max) {
        this.local = local;
        flags = new HashSet<Character>();
        List<String> argsList = new ArrayList<String>(tokens.length);
        for (String token : tokens) {
            if (token.isEmpty()) {
                // Do nothing
            } else if (token.charAt(0) == '-' && token.length() > 1
                    && Character.isLetter(token.charAt(1))) {
                for (char flag : token.substring(1).toCharArray()) {
                    if (!allowedFlag.contains(flag)) {
                        throw new CommandUsageError(String.format(local.invalidFlag(), flag));
                    }
                    flags.add(flag);
                }
            } else {
                argsList.add(token);
            }
        }
        if (argsList.size() < min) {
            throw new CommandUsageError(String.format(local.missingArguments(),
                    argsList.size(), min));
        }
        if (max != -1 && argsList.size() > max) {
            throw new CommandUsageError(String.format(local.tooManyArguments(),
                    argsList.size(), max));
        }
        args = argsList.toArray(new String[0]);
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

    public String get(int index, int endIndex) {
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

