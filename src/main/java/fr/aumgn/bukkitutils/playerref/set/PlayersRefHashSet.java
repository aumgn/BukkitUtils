package fr.aumgn.bukkitutils.playerref.set;

import java.util.HashSet;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import fr.aumgn.bukkitutils.playerref.PlayersIterable;
import fr.aumgn.bukkitutils.playerref.ToOfflinePlayer;

public class PlayersRefHashSet
            extends HashSet<PlayerRef> implements PlayersRefSet {

    private static final long serialVersionUID = -2701720565674845979L;

    public PlayersRefHashSet() {
        super();
    }

    @Override
    public boolean contains(OfflinePlayer player) {
        return contains(PlayerRef.get(player));
    }

    @Override
    public boolean add(OfflinePlayer player) {
        return add(PlayerRef.get(player));
    }

    @Override
    public boolean remove(OfflinePlayer player) {
        return remove(PlayerRef.get(player));
    }

    @Override
    public Iterable<OfflinePlayer> offlinePlayers() {
        return Iterables.transform(
                PlayersRefHashSet.this, new ToOfflinePlayer());
    }

    @Override
    public Iterable<Player> players() {
        return new PlayersIterable(PlayersRefHashSet.this);
    }

    @Override
    public List<OfflinePlayer> getOfflinePlayers() {
        return Lists.newArrayList(offlinePlayers());
    }

    @Override
    public List<Player> getPlayers() {
        return Lists.newArrayList(players());
    }
}
