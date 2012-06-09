package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.ChatColor;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class ChatColorArg extends CommandArg<ChatColor> {

    public static class Factory extends CommandArgFactory<ChatColor> {

        @Override
        public ChatColorArg createCommandArg(Messages messages, String string) {
            return new ChatColorArg(messages, string);
        }
    }

    public static class NoSuchColor extends CommandError {

        private static final long serialVersionUID = 2534023126371443961L;

        public NoSuchColor(Messages messages, String name) {
            super(String.format(messages.noSuchColor(), name));
        }
    }

    public ChatColorArg(Messages messages, String string) {
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
