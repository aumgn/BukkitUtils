package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.ChatColor;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public class ChatColorArg extends AbstractCommandArg<ChatColor> {

    public static class NoSuchColor extends CommandError {

        private static final long serialVersionUID = 2534023126371443961L;

        public NoSuchColor(CommandsMessages messages, String name) {
            super(messages.noSuchColor(name));
        }
    }

    public ChatColorArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public ChatColor value() {
        try {
            return ChatColor.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new NoSuchColor(messages, string);
        }
    }
}
