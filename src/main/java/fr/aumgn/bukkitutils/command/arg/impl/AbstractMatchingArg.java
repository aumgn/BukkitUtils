package fr.aumgn.bukkitutils.command.arg.impl;

import fr.aumgn.bukkitutils.command.arg.MatchingArg;

import java.util.Collections;
import java.util.List;

public abstract class AbstractMatchingArg<V> extends AbstractCommandArg<V>
        implements MatchingArg<V> {

    public AbstractMatchingArg(String string) {
        super(string);
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
