package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;

public class OfflinePlayerArg extends CommandArg<OfflinePlayer> {

    public static class Factory extends CommandArgFactory<OfflinePlayerArg> {

        @Override
        public OfflinePlayerArg createCommandArg(
                CommandsMessages messages, String string) {
            return new OfflinePlayerArg(messages, string);
        }
    }

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
