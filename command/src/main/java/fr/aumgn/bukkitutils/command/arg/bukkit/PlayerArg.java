package fr.aumgn.bukkitutils.command.arg.bukkit;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.glob.Glob;
import fr.aumgn.bukkitutils.glob.Glob.ToString;

public class PlayerArg extends CommandArg<Player> {

    public static class Factory extends CommandArgFactory<Player> {

        @Override
        public PlayerArg createCommandArg(Messages messages, String string) {
            return new PlayerArg(messages, string);
        }
    }

    public static class NoSuchPlayer extends CommandError {
        private static final long serialVersionUID = -4832133406864970323L;

        public NoSuchPlayer(Messages messages, String name) {
            super(messages.noSuchPlayer(name));
        }
    }

    private static class PlayerToString implements ToString<Player> {
        public String convert(Player player) {
            return player.getName();
        }
    }

    public PlayerArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Player value() {
        List<Player> players = match();

        if (players.size() > 1) {
            throw new NoSuchPlayer(messages, string);
        }

        return players.get(0);
    }

    @Override
    public Player value(CommandSender sender) {
        if (string != null) {
            return value();
        }

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

    @Override
    public List<Player> match(CommandSender sender) {
        if (string != null) {
            return match();
        }

        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.playerNeeded());
        }

        return Collections.<Player>singletonList((Player) sender);
    }
}
