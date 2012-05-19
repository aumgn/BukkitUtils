package fr.aumgn.bukkitutils.playerid.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public class PlayersIdHashMap<T>
            extends HashMap<PlayerId, T> implements PlayersIdMap<T> {

    private static final long serialVersionUID = -2270069935511356836L;

    public PlayersIdHashMap() {
        super();
    }

    @Override
    public boolean containsKey(OfflinePlayer player) {
        return containsKey(PlayerId.get(player));
    }

    @Override
    public T get(OfflinePlayer player) {
        return get(PlayerId.get(player));
    }

    @Override
    public void put(OfflinePlayer player, T value) {
        put(PlayerId.get(player), value);
    }

    @Override
    public Set<OfflinePlayer> offlinePlayersSet() {
        HashSet<OfflinePlayer> set = new HashSet<OfflinePlayer>();
        for (PlayerId account : keySet()) {
            set.add(account.getOfflinePlayer());
        }

        return set;
    }

    public Set<Player> playersSet() {
        HashSet<Player> set = new HashSet<Player>();
        for (PlayerId account : keySet()) {
            Player player = account.getPlayer();
            if (player != null) {
                set.add(player);
            }
        }

        return set;
    }

    @Override
    public Set<Entry<OfflinePlayer, T>> offlinePlayersEntrySet() {
        HashSet<Entry<OfflinePlayer, T>> set =
                new HashSet<Map.Entry<OfflinePlayer, T>>();
        for (Map.Entry<PlayerId, T> entry : entrySet()) {
            set.add(new OfflinePlayerMapEntry<T>(entry));
        }
        return set;
    }

    @Override
    public Set<Entry<Player, T>> playersEntrySet() {
        HashSet<Entry<Player, T>> set =
                new HashSet<Entry<Player, T>>();
        for (Entry<PlayerId, T> entry : entrySet()) {
            set.add(new PlayerMapEntry<T>(entry));
        }
        return set;
    }
}
