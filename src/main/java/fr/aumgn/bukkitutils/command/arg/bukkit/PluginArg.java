package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public class PluginArg extends CommandArg<Plugin> {

    public PluginArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    public static class Factory extends CommandArgFactory<Plugin> {

        @Override
        public PluginArg createCommandArg(CommandsMessages messages, String string) {
            return new PluginArg(messages, string);
        }
    }

    public static class NoSuchPlugin extends CommandError {

        private static final long serialVersionUID = 6331437020046703894L;

        public NoSuchPlugin(CommandsMessages messages, String name) {
            super(messages.noSuchPlugin(name));
        }
    }

    @Override
    public Plugin value() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(string);

        if (plugin == null) {
            throw new NoSuchPlugin(messages, string);
        }

        return plugin;
    }
}
