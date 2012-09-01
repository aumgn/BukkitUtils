package fr.aumgn.bukkitutils.playerref.map;

import java.util.Map;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerref.PlayerRef;

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
