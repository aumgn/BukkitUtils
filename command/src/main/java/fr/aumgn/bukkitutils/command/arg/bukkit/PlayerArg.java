package fr.aumgn.bukkitutils.command.arg.bukkit;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.PartialCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.util.Util;

public class PlayerArg extends PartialCommandArg<Player> {

    public static class Factory extends CommandArgFactory<Player> {

        @Override
        public PlayerArg createCommandArg(Messages messages, String string) {
            return new PlayerArg(messages, string);
        }
    }

    public static class NoSuchPlayer extends CommandError {
        private static final long serialVersionUID = -4832133406864970323L;

        public NoSuchPlayer(Messages messages, String name) {
            super(String.format(messages.noSuchPlayer(), name));
        }
    }

    public PlayerArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Player value() {
        Player player = Bukkit.getPlayer(string);
        if (player == null) {
            throw new NoSuchPlayer(messages, string);
        }

        return player;
    }

    @Override
    public Player value(CommandSender sender) {
        if (string != null) {
            return value();
        }

        if (!(sender instanceof Player)) {
            throw new CommandUsageError("You have to specify a player.");
        }

        return value((Player) sender);
    }

    @Override
    public List<Player> match() {
        if (string.equals("*")) {
            return Arrays.asList(Bukkit.getOnlinePlayers());
        }

        List<Player> players = Util.matchPlayer(string);
        if (players.isEmpty()) {
            throw new PlayerArg.NoSuchPlayer(messages, string);
        }

        return players;
    }

    @Override
    public List<Player> match(CommandSender sender) {
        if (string != null) {
            return match();
        }

        if (!(sender instanceof Player)) {
            throw new CommandUsageError("You have to specify a player.");
        }

        return match((Player) sender);
    }
}
