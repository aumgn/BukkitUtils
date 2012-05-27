package fr.aumgn.bukkitutils.command.args;

import java.util.List;

public interface CommandArgsInterface extends Iterable<String> {

    boolean hasFlags();

    Iterable<Character> flags();

    int length();

    List<String> asList();
}
