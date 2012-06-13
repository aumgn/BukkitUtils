package fr.aumgn.bukkitutils.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import fr.aumgn.bukkitutils.itemtype.ItemTypeDataParser;

public final class Util {

    private static final Random RANDOM = new Random();

    private Util() {
    }

    public static Random getRandom() {
        return RANDOM;
    }

    public static <T> T pickRandom(List<T> from) {
        if (from.isEmpty()) {
            return null;
        }
        return from.get(getRandom().nextInt(from.size()));
    }

    public static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    public static void broadcast(String permission, String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(message);
            }
        }
    }
    
    public static void broadcastOnlyToOp(String message) {
    	for (Player player : Bukkit.getOnlinePlayers()) {
    		if(player.isOp()) {
    			player.sendMessage(message);
    		}
    	}
    }

    public static List<Player> matchPlayer(String rawPattern) {
        String pattern = rawPattern.toLowerCase(Locale.ENGLISH);
        ArrayList<Player> players = new ArrayList<Player>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            String name = player.getName().toLowerCase(Locale.ENGLISH);
            if (name.equals(pattern)) {
                return Collections.<Player>singletonList(player);
            } else if (name.startsWith(pattern)) {
                players.add(player);
            }
        }

        return players;
    }

    public static List<OfflinePlayer> matchOfflinePlayer(String rawPattern) {
        String pattern = rawPattern.toLowerCase(Locale.ENGLISH);
        ArrayList<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            String name = player.getName().toLowerCase(Locale.ENGLISH);
            if (name.equals(pattern)) {
                return Collections.<OfflinePlayer>singletonList(player);
            } else if (name.startsWith(pattern)) {
                players.add(player);
            }
        }

        return players;
    }

    public static Material matchMaterial(String pattern) {
        if (pattern.equalsIgnoreCase("dye")) {
            return Material.INK_SACK;
        }

        return Material.matchMaterial(pattern);
    }

    public static Short parseDataFor(Material material, String token) {
        try {
            return Short.parseShort(token);
        } catch (NumberFormatException exc) {
        }

        ItemTypeDataParser parser = ItemTypeDataParser.getFor(material);
        if (parser == null) {
            return null;
        }

        return parser.parse(token);
    }
}
