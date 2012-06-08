package fr.aumgn.bukkitutils.command.arg.basic;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public class Vector2DArg extends CommandArg<Vector2D> {

    public static class Factory extends CommandArgFactory<Vector2D> {

        @Override
        public Vector2DArg createCommandArg(Messages messages, String string) {
            return new Vector2DArg(messages, string);
        }
    }

    public Vector2DArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Vector2D value() {
        String[] splitted = string.split(",");

        if (splitted.length > 2) {
            throw new CommandUsageError();
        }

        double x = VectorArg.parseVectorComponent(messages, splitted[0]);
        double z = 0.0;
        if (splitted.length > 1) {
            z = VectorArg.parseVectorComponent(messages, splitted[1]);
        }

        return new Vector2D(x,z);
    }

    @Override
    public Vector2D value(CommandSender sender) {
        if (string != null) {
            value();
        }

        if (!(sender instanceof Player)) {
            throw new CommandUsageError("You have to specify a position.");
        }

        return value(new Vector((Player) sender).to2D());
    }
}
