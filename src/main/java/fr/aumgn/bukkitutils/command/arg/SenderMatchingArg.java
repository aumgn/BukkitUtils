package fr.aumgn.bukkitutils.command.arg;

import org.bukkit.command.CommandSender;

import java.util.List;

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
