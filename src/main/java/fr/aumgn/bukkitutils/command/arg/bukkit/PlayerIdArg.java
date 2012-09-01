package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.playerid.PlayerId;

public class PlayerIdArg extends CommandArg<PlayerId> {

    public static class Factory extends CommandArgFactory<PlayerIdArg> {

        static {
            CommandArgFactory.register(PlayerIdArg.class, new Factory());
        }

        @Override
        public PlayerIdArg createCommandArg(CommandsMessages messages, String string) {
            return new PlayerIdArg(messages, string);
        }
    }

    public PlayerIdArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public PlayerId value() {
        return PlayerId.get(string);
    }

    @Override
    protected PlayerId defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return PlayerId.get((Player)sender);
    }
}
