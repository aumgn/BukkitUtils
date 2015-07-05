package fr.aumgn.bukkitutils.command.arg.impl;

import fr.aumgn.bukkitutils.command.arg.SenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import org.bukkit.command.CommandSender;

public abstract class AsbtractSenderArg<V>
        extends AbstractCommandArg<V> implements SenderArg<V> {

    public AsbtractSenderArg(String string) {
        super(string);
    }

    protected abstract V defaultFor(CommandSender sender);

    protected abstract String missingPermOtherMessage(String permission);

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
                throw new CommandError(missingPermOtherMessage(permission));
            }

            return value();
        }

        return defaultFor(sender);
    }
}
