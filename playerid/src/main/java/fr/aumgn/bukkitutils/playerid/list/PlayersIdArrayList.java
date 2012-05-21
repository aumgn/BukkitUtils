package fr.aumgn.bukkitutils.playerid.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;
import fr.aumgn.bukkitutils.playerid.iterator.OfflinePlayersIterator;
import fr.aumgn.bukkitutils.playerid.iterator.PlayersIterator;

public class PlayersIdArrayList
            extends ArrayList<PlayerId> implements PlayersIdList {

    private static final long serialVersionUID = -921106024183437981L;

    public PlayersIdArrayList() {
        super();
    }

    public PlayersIdArrayList(int capacity) {
        super(capacity);
    }

    @Override
    public void add(OfflinePlayer player) {
        add(PlayerId.get(player));
    }

    @Override
    public void add(int index, OfflinePlayer player) {
        add(index, PlayerId.get(player));
    }

    @Override
    public boolean contains(OfflinePlayer player) {
        return contains(PlayerId.get(player));
    }

    @Override
    public OfflinePlayer getOfflinePlayer(int index) {
        return get(index).getOfflinePlayer();
    }

    @Override
    public Player getPlayer(int index) {
        return get(index).getPlayer();
    }

    @Override
    public Iterable<OfflinePlayer> offlinePlayers() {
        return new Iterable<OfflinePlayer>() {
            public Iterator<OfflinePlayer> iterator() {
                return new OfflinePlayersIterator(
                        PlayersIdArrayList.this.iterator());
            }
        };
    }

    @Override
    public Iterable<Player> players() {
        return new Iterable<Player>() {
            public Iterator<Player> iterator() {
                return new PlayersIterator(
                        PlayersIdArrayList.this.iterator());
            }
        };
    }

    @Override
    public List<OfflinePlayer> getOfflinePlayers() {
        List<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
        for (OfflinePlayer player : offlinePlayers()) {
            players.add(player);
        }

        return players;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Player player : players()) {
            players.add(player);
        }

        return players;
    }
}
