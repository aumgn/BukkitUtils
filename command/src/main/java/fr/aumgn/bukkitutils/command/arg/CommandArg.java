package fr.aumgn.bukkitutils.command.arg;

import java.util.List;

import fr.aumgn.bukkitutils.command.messages.Messages;

public abstract class CommandArg<V> extends AbstractCommandArg<V, V> {

    public CommandArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public List<V> match() {
        throw new UnsupportedOperationException();
    }
}
