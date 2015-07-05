package fr.aumgn.bukkitutils.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {

    private static final Pattern COLORS_PATTERN =
            Pattern.compile("\\{([A-Za-z]+)\\}");
    private static final Random RANDOM = new Random();

    private Util() {
    }

    /**
     * Gets the shared Random objects.
     */
    public static Random getRandom() {
        return RANDOM;
    }

    /**
     * Picks a random object in the list.
     */
    public static <T> T pickRandom(List<T> from) {
        if (from.isEmpty()) {
            return null;
        }
        return from.get(getRandom().nextInt(from.size()));
    }

    /**
     * Call an event.
     */
    public static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    /**
     * Broadcast the message to all players.
     * Do not send it to Console contrary to
     * {@link Bukkit#broadcastMessage(String)}
     */
    public static void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    /**
     * Broadcast the message to all players
     * who have the given permission.
     * Do not send it to Console contrary to
     * {@link Bukkit#broadcastMessage(String)}
     */
    public static void broadcast(String permission, String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * Match a material. Wraps
     * {@link Material#matchMaterial(String)}
     * and add an support for `dye`.
     */
    public static Material matchMaterial(String pattern) {
        if (pattern.equalsIgnoreCase("dye")) {
            return Material.INK_SACK;
        }

        return Material.matchMaterial(pattern);
    }

    /**
     * Replaces all {ColorName} in the string by
     * the corresponding color code.
     */
    public static String parseColorsMarkup(String message) {
        StringBuffer parsed = new StringBuffer();
        Matcher matcher = COLORS_PATTERN.matcher(message);
        while (matcher.find()) {
            try {
                ChatColor color = ChatColor.valueOf(matcher.group(1)
                        .toUpperCase());
                matcher.appendReplacement(parsed, color.toString());
            }
            catch (IllegalArgumentException exc) {
            }
        }
        matcher.appendTail(parsed);
        return parsed.toString();
    }

    /**
     * Parse a locale identifier into a Locale.
     * Returns the system default Locale if
     * the string is invalid.
     */
    public static Locale parseLocale(String localeStr) {
        return parseLocale(localeStr, "_");
    }

    /**
     * Parse a locale identifier into a Locale using
     * the given delimiter. Returns the system
     * default Locale if the string is invalid.
     */
    public static Locale parseLocale(String localeStr, String delimiter) {
        String[] splitted = localeStr.split(delimiter);
        if (splitted.length == 0) {
            return Locale.getDefault();
        }
        else if (splitted.length == 1) {
            return new Locale(splitted[0]);
        }
        else {
            return new Locale(splitted[0], splitted[1]);
        }
    }
}
