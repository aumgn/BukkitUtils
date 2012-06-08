package fr.aumgn.bukkitutils.command.arg;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.messages.Messages;

public abstract class PartialCommandArg<V> extends CommandArg<V> {

    public PartialCommandArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public abstract List<V> match();

    @Override
    public List<V> match(List<V> def) {
        if (string == null) {
            return def;
        }

        return match();
    }

    @Override
    public List<V> match(V def) {
        if (string == null) {
            return Collections.<V>singletonList(def);
        }

        return match();
    }

    @Override
    public List<V> match(CommandSender sender) {
        throw new UnsupportedOperationException();
    }
}
