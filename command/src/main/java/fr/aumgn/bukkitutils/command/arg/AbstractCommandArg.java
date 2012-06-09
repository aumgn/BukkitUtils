package fr.aumgn.bukkitutils.command.arg;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.messages.Messages;

public abstract class AbstractCommandArg<V, W> {

    protected final Messages messages;
    protected final String string;

    public AbstractCommandArg(Messages messages, String string) {
        this.messages = messages;
        this.string = string;
    }

    public abstract V value();

    public V value(V def) {
        if (string == null) {
            return def;
        }

        return value();
    }

    public V value(CommandSender sender) {
        throw new UnsupportedOperationException();
    }

    public abstract List<W> match();

    public List<W> match(List<W> def) {
        if (string == null) {
            return def;
        }

        return match();
    }

    public List<W> match(W def) {
        if (string == null) {
            return Collections.<W>singletonList(def);
        }

        return match();
    }

    public List<W> match(CommandSender sender) {
        throw new UnsupportedOperationException();
    }

}
