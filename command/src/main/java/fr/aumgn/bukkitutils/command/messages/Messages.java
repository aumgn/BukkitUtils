package fr.aumgn.bukkitutils.command.messages;

import org.bukkit.ChatColor;

public class Messages {

    public String usagePrefix() {
        return ChatColor.GREEN + "Usage : " + ChatColor.YELLOW + "/<command> ";
    }

    public String permissionMessage() {
        return ChatColor.RED + "You don't have the permission to do that.";
    }

    public String playerOnly() {
        return ChatColor.RED + "This command can only be used as a player.";
    }

    public String missingEndingQuote() {
        return "Missing ending quote in %s.";
    }

    public String invalidFlag() {
        return "Invalid Flag(s) : %s.";
    }

    public String missingArguments() {
        return "Missing argument(s) (%d / %d min).";
    }

    public String tooManyArguments() {
        return "Too many argument(s) (%d / %d max).";
    }

    public String notAValidNumber() {
        return "Invalid number : %s.";
    }

    public String unexpectedError() {
        return "An unexpected error ocurred while trying to execute command.";
    }

    public String noSuchColor() {
        return "No such color %s.";
    }

    public String noSuchWorld() {
        return "No such world %s.";
    }

    public String noSuchPlayer() {
        return "No such player %s.";
    }

    public String noSuchMaterial() {
        return "No such material %s.";
    }

    public String invalidMaterialAndDataFormat() {
        return "Invalid material and data format : %s.";
    }

    public String notAValidVectorComponent() {
        return "Invalid vector component : %s.";
    }

    public String noSuchPotionEffect() {
        return "No such potion effect %s.";
    }

    public String noSuchEnchant() {
        return "No such enchant %s.";
    }

    public String noSuchEntityType() {
        return "No such entity (%s).";
    }

    public String playerNeeded() {
        return "You have to specify a player.";
    }

    public String worldNeeded() {
        return "You have to specify a world.";
    }

    public String positionNeeded() {
        return "You have to specify a position.";
    }
}
