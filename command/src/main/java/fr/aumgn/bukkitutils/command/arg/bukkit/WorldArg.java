package fr.aumgn.bukkitutils.command.arg.bukkit;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.glob.Glob;
import fr.aumgn.bukkitutils.glob.Glob.ToString;

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
            super(messages.noSuchWorld(name));
        }
    }

    public static class MoreThanOneWorldFound extends CommandError {
        private static final long serialVersionUID = 7101062818304484950L;

        public MoreThanOneWorldFound(Messages messages, String name) {
            super(messages.moreThanOneWorldFound(name));
        }
    }

    private static class WorldToString implements ToString<World> {
        public String convert(World world) {
            return world.getName();
        }
    }

    @Override
    public World value() {
        List<World> worlds = match();
        if (worlds.size() > 1) {
            throw new MoreThanOneWorldFound(messages, string);
        }

        return worlds.get(0);
    }

    @Override
    protected World defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError("You have to specify a world.");
        }

        return ((Player) sender).getWorld();
    }

    @Override
    public List<World> match() {
        List<World> worlds = new Glob(string)
                .partial().caseInsensitive()
                .build(new WorldToString())
                .filter(Bukkit.getWorlds());

        if (worlds.isEmpty()) {
            throw new NoSuchWorld(messages, string);
        }

        return worlds;
    }
}
