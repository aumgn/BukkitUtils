package fr.aumgn.bukkitutils.playerref.map;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayersRefHashMap<T>
        extends HashMap<PlayerRef, T> implements PlayersRefMap<T> {

    private static final long serialVersionUID = -2270069935511356836L;

    public PlayersRefHashMap() {
        super();
    }

    @Override
    public boolean containsKey(OfflinePlayer player) {
        return containsKey(PlayerRef.get(player));
    }

    @Override
    public T get(OfflinePlayer player) {
        return get(PlayerRef.get(player));
    }

    @Override
    public T put(OfflinePlayer player, T value) {
        return put(PlayerRef.get(player), value);
    }

    @Override
    public T remove(OfflinePlayer player) {
        return remove(PlayerRef.get(player));
    }

    @Override
    public Set<OfflinePlayer> offlinePlayersSet() {
        HashSet<OfflinePlayer> set = new HashSet<OfflinePlayer>();
        for (PlayerRef ref : keySet()) {
            set.add(ref.getOfflinePlayer());
        }

        return set;
    }

    public Set<Player> playersSet() {
        HashSet<Player> set = new HashSet<Player>();
        for (PlayerRef ref : keySet()) {
            Player player = ref.getPlayer();
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
        for (Map.Entry<PlayerRef, T> entry : entrySet()) {
            set.add(new OfflinePlayerMapEntry<T>(entry));
        }
        return set;
    }

    @Override
    public Set<Entry<Player, T>> playersEntrySet() {
        HashSet<Entry<Player, T>> set =
                new HashSet<Entry<Player, T>>();
        for (Entry<PlayerRef, T> entry : entrySet()) {
            set.add(new PlayerMapEntry<T>(entry));
        }
        return set;
    }
}
