package fr.aumgn.bukkitutils.command.arg.impl;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.MatchingArg;
import fr.aumgn.bukkitutils.command.arg.SenderArg;
import fr.aumgn.bukkitutils.command.arg.SenderMatchingArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public abstract class AbstractSenderMatchingArg<V>
        extends AsbtractSenderArg<V>
        implements MatchingArg<V>, SenderArg<V>, SenderMatchingArg<V> {

    public AbstractSenderMatchingArg(CommandsMessages messages,
            String string) {
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

    @Override
    public List<V> matchOr(CommandSender sender) {
        if (string != null) {
            return match();
        }

        return Collections.singletonList(defaultFor(sender));
    }

    @Override
    public List<V> matchWithPermOr(String permission, CommandSender sender) {
        if (string != null) {
            if (!sender.hasPermission(permission)) {
                throw new CommandError(
                        messages.missingPermissionForOther(permission));
            }

            return match();
        }

        return Collections.singletonList(defaultFor(sender));
    }
}
