package fr.aumgn.bukkitutils.playerid.set;

import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public interface PlayersIdSet extends Set<PlayerId> {

    boolean contains(OfflinePlayer player);

    boolean add(OfflinePlayer player);

    boolean remove(OfflinePlayer player);

    Iterable<OfflinePlayer> offlinePlayers();

    Iterable<Player> players();

    List<OfflinePlayer> getOfflinePlayers();

    List<Player> getPlayers();
}
