package fr.aumgn.bukkitutils.command.arg.impl;

import java.util.Collections;
import java.util.List;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.MatchingArg;

public abstract class AbstractMatchingArg<V> extends AbstractCommandArg<V>
        implements MatchingArg<V> {

    public AbstractMatchingArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public List<V> matchOr(V def) {
        if (string == null) {
            return Collections.singletonList(def);
        }

        return match();
    }

    @Override
    public List<V> matchOr(List<V> def) {
        if (string == null) {
            return def;
        }

        return match();
    }
}
