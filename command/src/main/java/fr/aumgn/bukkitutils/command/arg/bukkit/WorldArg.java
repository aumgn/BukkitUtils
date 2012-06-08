package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class WorldArg extends CommandArg<World> {

    public static class Factory extends CommandArgFactory<World> {

        @Override
        public WorldArg createCommandArg(Messages messages, String string) {
            return new WorldArg(messages, string);
        }
    }

    public WorldArg(Messages messages, String string) {
        super(messages, string);
    }

    public static class NoSuchWorld extends CommandError {
        private static final long serialVersionUID = -4832133406864970323L;

        public NoSuchWorld(Messages messages, String name) {
            super(String.format(messages.noSuchWorld(), name));
        }
    }

    @Override
    public World value() {
        World world = Bukkit.getWorld(string);
        if (world == null) {
            throw new NoSuchWorld(messages, string);
        }

        return world;
    }

    @Override
    public World value(CommandSender sender) {
        if (string != null) {
            return value();
        }

        if (!(sender instanceof Player)) {
            throw new CommandUsageError("You have to specify a world.");
        }

        return value(((Player) sender).getWorld());
    }
}
