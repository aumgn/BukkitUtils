package fr.aumgn.bukkitutils.playerid.map;

import java.util.Map;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public interface PlayersIdMap<T> extends Map<PlayerId, T> {

    boolean containsKey(OfflinePlayer player);

    T get(OfflinePlayer player);

    T put(OfflinePlayer player, T value);

    T remove(OfflinePlayer player);

    Set<OfflinePlayer> offlinePlayersSet();

    Set<Player> playersSet();

    Set<Map.Entry<OfflinePlayer, T>> offlinePlayersEntrySet();

    Set<Map.Entry<Player, T>> playersEntrySet();
}
