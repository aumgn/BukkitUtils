package fr.aumgn.bukkitutils.command.arg.impl;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.SenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public abstract class AsbtractSenderArg<V>
        extends AbstractCommandArg<V> implements SenderArg<V> {

    public AsbtractSenderArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    protected abstract V defaultFor(CommandSender sender);

    @Override
    public V valueOr(CommandSender sender) {
        if (string != null) {
            return value();
        }

        return defaultFor(sender);
    }

    @Override
    public V valueWithPermOr(String permission, CommandSender sender) {
        if (string != null) {
            if (!sender.hasPermission(permission)) {
                throw new CommandError(
                        messages.missingPermissionForOther(permission));
            }

            return value();
        }

        return defaultFor(sender);
    }
}
