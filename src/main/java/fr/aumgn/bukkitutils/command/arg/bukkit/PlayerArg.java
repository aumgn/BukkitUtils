package fr.aumgn.bukkitutils.command.arg.bukkit;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Function;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractSenderMatchingArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.glob.Glob;

public class PlayerArg extends AbstractSenderMatchingArg<Player> {

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

    public PlayerArg(CommandsMessages messages, String string) {
        super(messages, string);
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
    public List<Player> match() {
        List<Player> players = new Glob(string)
                .start().caseInsensitive()
                .build(new PlayerToString())
                .filter(Bukkit.getOnlinePlayers());

        if (players.isEmpty()) {
            throw new NoSuchPlayer(messages, string);
        }

        return players;
    }
}
