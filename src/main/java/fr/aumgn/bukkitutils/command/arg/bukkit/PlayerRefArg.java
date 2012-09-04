package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AsbtractSenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.playerref.PlayerRef;

public class PlayerRefArg extends AsbtractSenderArg<PlayerRef> {

    private final CommandsMessages messages;

    public PlayerRefArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
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

    @Override
    protected String missingPermOtherMessage(String permission) {
        return messages.missingPermissionForOther(permission);
    }
}
