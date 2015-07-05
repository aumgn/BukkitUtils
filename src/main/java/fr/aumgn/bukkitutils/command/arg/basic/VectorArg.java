package fr.aumgn.bukkitutils.command.arg.basic;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AsbtractSenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.geom.Vector;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VectorArg extends AsbtractSenderArg<Vector> {

    private final CommandsMessages messages;

    public VectorArg(CommandsMessages messages, String string) {
        super(string);
        this.messages = messages;
    }

    static double parseVectorComponent(CommandsMessages messages, String component) {
        try {
            return Double.parseDouble(component);
        }
        catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    messages.notAValidVectorComponent(component));
        }
    }

    @Override
    public Vector value() {
        String[] splitted = string.split(",");

        if (splitted.length > 3) {
            throw new CommandUsageError();
        }

        double x = parseVectorComponent(messages, splitted[0]);
        double y = 0.0;
        double z = 0.0;
        if (splitted.length > 1) {
            y = parseVectorComponent(messages, splitted[1]);
            if (splitted.length > 2) {
                z = parseVectorComponent(messages, splitted[2]);
            }
        }

        return new Vector(x, y, z);
    }

    @Override
    protected Vector defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new CommandUsageError(messages.positionNeeded());
        }

        return new Vector((Player) sender);
    }

    @Override
    protected String missingPermOtherMessage(String permission) {
        return messages.missingPermissionForOther(permission);
    }
}
