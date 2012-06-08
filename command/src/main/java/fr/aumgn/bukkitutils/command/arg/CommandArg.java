package fr.aumgn.bukkitutils.command.arg;

import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.messages.Messages;

public abstract class CommandArg<V> {

    protected final Messages messages;
    protected final String string;

    public CommandArg(Messages messages, String string) {
        this.messages = messages;
        this.string = string;
    }

    public String string() {
        return string;
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

    public List<V> match() {
        throw new UnsupportedOperationException();
    }

    public List<V> match(List<V> def) {
        throw new UnsupportedOperationException();
    }

    public List<V> match(V def) {
        throw new UnsupportedOperationException();
    }

    public List<V> match(CommandSender sender) {
        throw new UnsupportedOperationException();
    }
}
