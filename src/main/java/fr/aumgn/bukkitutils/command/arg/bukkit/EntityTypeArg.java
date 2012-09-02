package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.entity.EntityType;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public class EntityTypeArg extends AbstractCommandArg<EntityType> {

    public static class NoSuchEntityType extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchEntityType(CommandsMessages messages, String name) {
            super(messages.noSuchEntityType(name));
        }
    }

    public EntityTypeArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public EntityType value() {
        EntityType entityType = EntityType.fromName(string);
        if(entityType == null) {
            throw new NoSuchEntityType(messages, string);
        }

        return entityType;
    }
}
