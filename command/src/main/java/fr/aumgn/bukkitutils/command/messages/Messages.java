package fr.aumgn.bukkitutils.command.messages;

import org.bukkit.ChatColor;

import static java.lang.String.format;

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

    public String missingEndingQuote(String command) {
        return format("Missing ending quote in %s.", command);
    }

    public String invalidFlag(String flags) {
        return format("Invalid Flag(s) : %s.", flags);
    }

    public String missingArguments(int given, int min) {
        return format("Missing argument(s) (%d / %d min).", given, min);
    }

    public String tooManyArguments(int given, int max) {
        return format("Too many argument(s) (%d / %d max).", given, max);
    }

    public String notAValidNumber(String token) {
        return format("Invalid number : %s.", token);
    }

    public String unexpectedError() {
        return "An unexpected error ocurred while trying to execute command.";
    }

    public String noSuchColor(String token) {
        return format("No such color %s.", token);
    }

    public String noSuchWorld(String token) {
        return format("No such world %s.", token);
    }

    public String noSuchPlayer(String token) {
        return format("No such player %s.", token);
    }

    public String noSuchMaterial(String token) {
        return format("No such material %s.", token);
    }

    public String invalidMaterialAndDataFormat(String token) {
        return format("Invalid material and data format : %s.", token);
    }

    public String notAValidVectorComponent(String token) {
        return format("Invalid vector component : %s.", token);
    }

    public String noSuchPotionEffect(String token) {
        return format("No such potion effect %s.", token);
    }

    public String noSuchEnchant(String token) {
        return format("No such enchant %s.", token);
    }

    public String noSuchEntityType(String token) {
        return format("No such entity (%s).", token);
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

    public String unknownTimeFormat(String token) {
        return format("Unknown time format %s.", token);
    }

    public String unknownTimePeriod(String token) {
        return format("Unknown time period %s (expected am or pm).", token);
    }
}
