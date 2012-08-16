package fr.aumgn.bukkitutils.command.arg;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.Messages;

public abstract class CommandArg<V> extends AbstractCommandArg<V, V> {

    public CommandArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public List<V> match() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected List<V> defaultMatchFor(CommandSender sender) {
        return Collections.singletonList(defaultFor(sender));
    }
}
