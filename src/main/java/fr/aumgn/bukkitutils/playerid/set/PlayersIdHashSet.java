package fr.aumgn.bukkitutils.playerid.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;
import fr.aumgn.bukkitutils.playerid.iterator.OfflinePlayersIterator;
import fr.aumgn.bukkitutils.playerid.iterator.PlayersIterator;

public class PlayersIdHashSet
            extends HashSet<PlayerId> implements PlayersIdSet {

    private static final long serialVersionUID = -2701720565674845979L;

    public PlayersIdHashSet() {
        super();
    }

    @Override
    public boolean contains(OfflinePlayer player) {
        return contains(PlayerId.get(player));
    }

    @Override
    public boolean add(OfflinePlayer player) {
        return add(PlayerId.get(player));
    }

    @Override
    public boolean remove(OfflinePlayer player) {
        return remove(PlayerId.get(player));
    }

    @Override
    public Iterable<OfflinePlayer> offlinePlayers() {
        return new Iterable<OfflinePlayer>() {
            public Iterator<OfflinePlayer> iterator() {
                return new OfflinePlayersIterator(
                        PlayersIdHashSet.this.iterator());
            }
        };
    }

    @Override
    public Iterable<Player> players() {
        return new Iterable<Player>() {
            public Iterator<Player> iterator() {
                return new PlayersIterator(
                        PlayersIdHashSet.this.iterator());
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
