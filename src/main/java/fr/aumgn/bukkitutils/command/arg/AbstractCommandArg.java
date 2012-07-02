package fr.aumgn.bukkitutils.command.arg;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.exception.CommandError;
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

    protected V defaultFor(CommandSender sender) {
        throw new UnsupportedOperationException();
    }

    public V value(CommandSender sender) {
        if (string != null) {
            return value();
        }

        return defaultFor(sender);
    }

    public V value(CommandSender sender, String permissionOther) {
        if (string != null) {
            if (!sender.hasPermission(permissionOther)) {
                throw new CommandError(
                        messages.missingPermissionForOther(permissionOther));
            }

            return value();
        }

        return defaultFor(sender);
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

    protected List<W> defaultMatchFor(CommandSender sender) {
        throw new UnsupportedOperationException();
    }

    public List<W> match(CommandSender sender) {
        if (string != null) {
            return match();
        }

        return defaultMatchFor(sender);
    }

    public List<W> match(CommandSender sender, String permissionOther) {
        if (string != null) {
            if (!sender.hasPermission(permissionOther)) {
                throw new CommandError(
                        messages.missingPermissionForOther(permissionOther));
            }

            return match();
        }

        return defaultMatchFor(sender);
    }
}
