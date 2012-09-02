package fr.aumgn.bukkitutils.command.arg.impl;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;

public abstract class AbstractCommandArg<V> implements CommandArg<V> {

    protected final CommandsMessages messages;
    protected final String string;

    public AbstractCommandArg(CommandsMessages messages, String string) {
        this.messages = messages;
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
