package fr.aumgn.bukkitutils.playerref.set;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public interface PlayersRefSet extends Set<PlayerRef> {

    boolean contains(OfflinePlayer player);

    boolean add(OfflinePlayer player);

    boolean remove(OfflinePlayer player);

    Iterable<OfflinePlayer> offlinePlayers();

    Iterable<Player> players();

    List<OfflinePlayer> getOfflinePlayers();

    List<Player> getPlayers();
}
