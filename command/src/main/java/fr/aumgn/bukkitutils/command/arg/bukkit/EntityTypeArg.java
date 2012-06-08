package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.entity.EntityType;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class EntityTypeArg extends CommandArg<EntityType> {

    public static class Factory extends CommandArgFactory<EntityType> {

        @Override
        public EntityTypeArg createCommandArg(Messages messages, String string) {
            return new EntityTypeArg(messages, string);
        }
    }

    private static class NoSuchEntityType extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchEntityType(Messages messages, String name) {
            super(String.format(messages.noSuchEntityType(), name));
        }
    }

    public EntityTypeArg(Messages messages, String string) {
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
