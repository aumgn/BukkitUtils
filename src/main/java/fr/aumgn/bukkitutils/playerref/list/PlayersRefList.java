package fr.aumgn.bukkitutils.playerref.list;

import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerref.PlayerRef;

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
