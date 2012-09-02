package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AsbtractSenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class OfflinePlayerArg extends AsbtractSenderArg<OfflinePlayer> {

    public OfflinePlayerArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public OfflinePlayer value() {
        return Bukkit.getOfflinePlayer(string);
    }

    @Override
    protected OfflinePlayer defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return (Player) sender;
    }
}
