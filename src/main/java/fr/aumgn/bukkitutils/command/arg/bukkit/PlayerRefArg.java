package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.playerref.PlayerRef;

public class PlayerRefArg extends CommandArg<PlayerRef> {

    public static class Factory extends CommandArgFactory<PlayerRefArg> {

        @Override
        public PlayerRefArg createCommandArg(CommandsMessages messages, String string) {
            return new PlayerRefArg(messages, string);
        }
    }

    public PlayerRefArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public PlayerRef value() {
        return PlayerRef.get(string);
    }

    @Override
    protected PlayerRef defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return PlayerRef.get((Player)sender);
    }
}
