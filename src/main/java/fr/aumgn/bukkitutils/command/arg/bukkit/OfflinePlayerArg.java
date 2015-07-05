package fr.aumgn.bukkitutils.command.arg.bukkit;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractSenderMatchingArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.glob.Glob;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.List;

public class OfflinePlayerArg extends AbstractSenderMatchingArg<OfflinePlayer> {

    private final CommandsMessages messages;

    public OfflinePlayerArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public OfflinePlayer value() {
        List<OfflinePlayer> players = match();

        if (players.size() > 1) {
            throw new MoreThanOneOfflinePlayerFound(messages, string);
        }

        return players.get(0);
    }

    @Override
    protected OfflinePlayer defaultFor(CommandSender sender) {
        if (!(sender instanceof OfflinePlayer)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return (OfflinePlayer) sender;
    }

    @Override
    protected String missingPermOtherMessage(String permission) {
        return messages.missingPermissionForOther(permission);
    }

    @Override
    public List<OfflinePlayer> match() {
        ImmutableList<OfflinePlayer> offlinePlayers = new Glob(string)
                .fromStart().caseInsensitive()
                .build(new OfflinePlayerToString())
                .filter(Bukkit.getOfflinePlayers());

        if (offlinePlayers.isEmpty()) {
            throw new NoSuchOfflinePlayer(messages, string);
        }

        return offlinePlayers;
    }

    public static class NoSuchOfflinePlayer extends CommandError {
        public NoSuchOfflinePlayer(CommandsMessages messages, String name) {
            super(messages.noSuchPlayer(name));
        }
    }

    public static class MoreThanOneOfflinePlayerFound extends CommandError {
        public MoreThanOneOfflinePlayerFound(CommandsMessages messages, String name) {
            super(messages.moreThanOnePlayerFound(name));
        }
    }

    private static class OfflinePlayerToString implements Function<OfflinePlayer, String> {
        @Override
        public String apply(OfflinePlayer player) {
            return player.getName();
        }
    }
}
