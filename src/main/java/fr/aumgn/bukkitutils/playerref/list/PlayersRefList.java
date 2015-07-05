package fr.aumgn.bukkitutils.playerref.list;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayersRefList extends List<PlayerRef> {

    void add(OfflinePlayer player);

    void add(int index, OfflinePlayer player);

    boolean contains(OfflinePlayer player);

    OfflinePlayer getOfflinePlayer(int index);

    Player getPlayer(int index);

    Iterable<OfflinePlayer> offlinePlayers();

    Iterable<Player> players();

    List<OfflinePlayer> getOfflinePlayers();

    List<Player> getPlayers();
}
