package fr.aumgn.bukkitutils.command.arg.bukkit;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractSenderMatchingArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.glob.Glob;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerArg extends AbstractSenderMatchingArg<Player> {

    private final CommandsMessages messages;

    public PlayerArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public Player value() {
        List<Player> players = match();

        if (players.size() > 1) {
            throw new MoreThanOnePlayerFound(messages, string);
        }

        return players.get(0);
    }

    @Override
    protected Player defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return (Player) sender;
    }

    @Override
    protected String missingPermOtherMessage(String permission) {
        return messages.missingPermissionForOther(permission);
    }

    @Override
    public List<Player> match() {
        ImmutableList<? extends Player> players = new Glob(string)
                .fromStart().caseInsensitive()
                .build(new PlayerToString())
                .filter(Bukkit.getOnlinePlayers());

        if (players.isEmpty()) {
            throw new NoSuchPlayer(messages, string);
        }

        // Immutable, hence safe cast
        return (List<Player>) players;
    }

    public static class NoSuchPlayer extends CommandError {
        private static final long serialVersionUID = -4832133406864970323L;

        public NoSuchPlayer(CommandsMessages messages, String name) {
            super(messages.noSuchPlayer(name));
        }
    }

    public static class MoreThanOnePlayerFound extends CommandError {
        private static final long serialVersionUID = 7101062818304484950L;

        public MoreThanOnePlayerFound(CommandsMessages messages, String name) {
            super(messages.moreThanOnePlayerFound(name));
        }
    }

    private static class PlayerToString implements Function<Player, String> {
        @Override
        public String apply(Player player) {
            return player.getName();
        }
    }
}
