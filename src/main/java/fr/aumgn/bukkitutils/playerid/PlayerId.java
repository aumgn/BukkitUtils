package fr.aumgn.bukkitutils.playerid;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public final class PlayerId {

    private static final Map<String, PlayerId> accounts = 
                new HashMap<String, PlayerId>();

    public static PlayerId get(OfflinePlayer player) {
        return get(player.getName());
    }

    public static PlayerId get(String name) {
        String lname = name.toLowerCase(Locale.ENGLISH);
        if (!accounts.containsKey(lname)) {
            accounts.put(lname, new PlayerId(lname));
        }

        return accounts.get(lname);
    }

    private final String name;

    private PlayerId(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        if (!(other instanceof PlayerId)) {
            return false;
        }

        return name.equals(((PlayerId) other).name);
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
