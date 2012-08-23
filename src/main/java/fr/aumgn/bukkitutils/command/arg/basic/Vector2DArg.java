package fr.aumgn.bukkitutils.command.arg.basic;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public class Vector2DArg extends CommandArg<Vector2D> {

    public static class Factory extends CommandArgFactory<Vector2D> {

        @Override
        public Vector2DArg createCommandArg(CommandsMessages messages, String string) {
            return new Vector2DArg(messages, string);
        }
    }

    public Vector2DArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public Vector2D value() {
        String[] splitted = string.split(",");

        if (splitted.length > 3) {
            throw new CommandUsageError();
        }

        double x = VectorArg.parseVectorComponent(messages, splitted[0]);
        double z;
        switch (splitted.length) {
            case 2:
                z = VectorArg.parseVectorComponent(messages, splitted[1]);
                break;
            case 3:
                z = VectorArg.parseVectorComponent(messages, splitted[2]);
                break;
            default:
                z = 0.0;
        }

        return new Vector2D(x, z);
    }

    @Override
    protected Vector2D defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.positionNeeded());
        }

        return new Vector((Player) sender).to2D();
    }
}
