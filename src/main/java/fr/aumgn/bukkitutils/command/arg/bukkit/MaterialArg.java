package fr.aumgn.bukkitutils.command.arg.bukkit;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.util.Util;
import org.bukkit.Material;

public class MaterialArg extends AbstractCommandArg<Material> {

    private final CommandsMessages messages;

    public MaterialArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    static Material getMaterial(CommandsMessages messages, String pattern) {
        Material material = Util.matchMaterial(pattern);
        if (material == null) {
            throw new MaterialArg.NoSuchMaterial(messages, pattern);
        }

        return material;
    }

    @Override
    public Material value() {
        return getMaterial(messages, string);
    }

    public static class NoSuchMaterial extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchMaterial(CommandsMessages messages, String name) {
            super(messages.noSuchMaterial(name));
        }
    }
}
