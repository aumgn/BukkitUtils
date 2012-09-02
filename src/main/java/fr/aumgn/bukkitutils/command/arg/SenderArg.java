package fr.aumgn.bukkitutils.command.arg;

import org.bukkit.command.CommandSender;

public interface SenderArg<V> {

    /**
     * Returns the parsed value or compute it from
     * the given CommandSender if argument is omitted.
     */
    V valueOr(CommandSender sender);

    /**
     * Same as {@link #valueOr(CommandSender)}
     * but also check the given permission if
     * the argument is specified.
     */
    V valueWithPermOr(String permission, CommandSender sender);
}
