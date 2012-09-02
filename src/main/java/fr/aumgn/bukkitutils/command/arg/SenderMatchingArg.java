package fr.aumgn.bukkitutils.command.arg;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface SenderMatchingArg<V> {

    /**
     * Returns all values which match the argument or
     * the given CommandSender if argument is omitted.
     */
    List<V> matchOr(CommandSender sender);

    /**
     * Same as {@link #matchOr(CommandSender)}
     * but also check the given permission if
     * the argument is specified.
     */
    List<V> matchWithPermOr(String permission, CommandSender sender);
}
