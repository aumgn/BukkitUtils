package fr.aumgn.bukkitutils.playerref;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Hold a reference to a player.
 */
public final class PlayerRef {

    private static final Map<String, PlayerRef> playersRef =
                new HashMap<String, PlayerRef>();

    public static PlayerRef get(OfflinePlayer player) {
        return get(player.getName());
    }

    public static PlayerRef get(String name) {
        String lname = name.toLowerCase(Locale.ENGLISH);
        if (!playersRef.containsKey(lname)) {
            playersRef.put(lname, new PlayerRef(lname));
        }

        return playersRef.get(lname);
    }

    private final String name;

    private PlayerRef(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        Player player = getPlayer();
        if (player != null) {
            return player.getDisplayName();
        } else {
            return getName();
        }
    }

    public boolean isOnline() {
        return (getPlayer() != null);
    }

    public boolean isOffline() {
        return (getPlayer() == null);
    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(name);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(name);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PlayerRef)) {
            return false;
        }

        return name.equals(((PlayerRef) other).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
