package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Material;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.util.Util;

public class MaterialArg extends AbstractCommandArg<Material> {

    public static class NoSuchMaterial extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchMaterial(CommandsMessages messages, String name) {
            super(messages.noSuchMaterial(name));
        }
    }

    static Material getMaterial(CommandsMessages messages, String pattern) {
        Material material = Util.matchMaterial(pattern);
        if (material == null) {
            throw new MaterialArg.NoSuchMaterial(messages, pattern);
        }

        return material;
    }

    public MaterialArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Material value() {
        return getMaterial(messages, string);
    }
}
