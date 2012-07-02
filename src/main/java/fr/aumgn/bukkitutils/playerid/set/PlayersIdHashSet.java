package fr.aumgn.bukkitutils.playerid.set;

import java.util.HashSet;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.aumgn.bukkitutils.playerid.PlayerId;
import fr.aumgn.bukkitutils.playerid.PlayersIterable;
import fr.aumgn.bukkitutils.playerid.ToOfflinePlayer;

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
        return Iterables.transform(
                PlayersIdHashSet.this, new ToOfflinePlayer());
    }

    @Override
    public Iterable<Player> players() {
        return new PlayersIterable(PlayersIdHashSet.this);
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
