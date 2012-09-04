package fr.aumgn.bukkitutils.command.arg.impl;

import fr.aumgn.bukkitutils.command.arg.CommandArg;

public abstract class AbstractCommandArg<V> implements CommandArg<V> {

    protected final String string;

    public AbstractCommandArg(String string) {
        this.string = string;
    }

    @Override
    public V valueOr(V def) {
        if (string == null) {
            return def;
        }

        return value();
    }
}
