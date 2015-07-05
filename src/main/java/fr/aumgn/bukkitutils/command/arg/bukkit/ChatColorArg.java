package fr.aumgn.bukkitutils.command.arg.bukkit;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import org.bukkit.ChatColor;

public class ChatColorArg extends AbstractCommandArg<ChatColor> {

    private final CommandsMessages messages;

    public ChatColorArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public ChatColor value() {
        try {
            return ChatColor.valueOf(string.toUpperCase());
        }
        catch (IllegalArgumentException exc) {
            throw new NoSuchColor(messages, string);
        }
    }

    public static class NoSuchColor extends CommandError {

        private static final long serialVersionUID = 2534023126371443961L;

        public NoSuchColor(CommandsMessages messages, String name) {
            super(messages.noSuchColor(name));
        }
    }
}
