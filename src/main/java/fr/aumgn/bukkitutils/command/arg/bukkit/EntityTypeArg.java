package fr.aumgn.bukkitutils.command.arg.bukkit;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import org.bukkit.entity.EntityType;

public class EntityTypeArg extends AbstractCommandArg<EntityType> {

    private final CommandsMessages messages;

    public EntityTypeArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    @Override
    public EntityType value() {
        // Magic value, yeah maybe, but there do not seem
        // to be better alternatives available right now
        @SuppressWarnings("deprecation")
        EntityType entityType = EntityType.fromName(string);
        if (entityType == null) {
            throw new NoSuchEntityType(messages, string);
        }

        return entityType;
    }

    public static class NoSuchEntityType extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchEntityType(CommandsMessages messages, String name) {
            super(messages.noSuchEntityType(name));
        }
    }
}
