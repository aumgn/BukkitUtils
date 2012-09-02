package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AsbtractSenderArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.itemtype.ItemType;

import fr.aumgn.bukkitutils.util.Util;

public class ItemTypeArg extends AsbtractSenderArg<ItemType> {

    public static class InvalidItemTypeFormat extends CommandError {
        private static final long serialVersionUID = 1L;

        public InvalidItemTypeFormat(CommandsMessages messages, String given) {
            super(messages.invalidMaterialAndDataFormat(given));
        }
    }

    public ItemTypeArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public ItemType value() {
        String[] splitted = string.split(":");
        if (splitted.length > 2) {
            throw new InvalidItemTypeFormat(messages, string);
        }

        Material material = MaterialArg.getMaterial(messages, splitted[0]);
        Short data = 0;
        if (splitted.length == 2) {
            data = Util.parseDataFor(material, splitted[1]);
            if (data == null) {
                throw new InvalidItemTypeFormat(messages, string);
            }
        }

        return new ItemType(material, data);
    }

    @Override
    protected ItemType defaultFor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            throw new RuntimeException();
        }

        return new ItemType(((Player) sender).getItemInHand());
    }
}
