package fr.aumgn.bukkitutils.playerref.map;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;

public interface PlayersRefMap<T> extends Map<PlayerRef, T> {

    boolean containsKey(OfflinePlayer player);

    T get(OfflinePlayer player);

    T put(OfflinePlayer player, T value);

    T remove(OfflinePlayer player);

    Set<OfflinePlayer> offlinePlayersSet();

    Set<Player> playersSet();

    Set<Map.Entry<OfflinePlayer, T>> offlinePlayersEntrySet();

    Set<Map.Entry<Player, T>> playersEntrySet();
}
