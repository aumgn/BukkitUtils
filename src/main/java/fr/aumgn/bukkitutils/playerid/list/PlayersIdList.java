package fr.aumgn.bukkitutils.playerid.list;

import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public interface PlayersIdList extends List<PlayerId> {

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
