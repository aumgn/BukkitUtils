package fr.aumgn.bukkitutils.playerref.list;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import fr.aumgn.bukkitutils.playerref.PlayersIterable;
import fr.aumgn.bukkitutils.playerref.ToOfflinePlayer;

public class PlayersRefArrayList
            extends ArrayList<PlayerRef> implements PlayersRefList {

    private static final long serialVersionUID = -921106024183437981L;

    public PlayersRefArrayList() {
        super();
    }

    public PlayersRefArrayList(int capacity) {
        super(capacity);
    }

    @Override
    public void add(OfflinePlayer player) {
        add(PlayerRef.get(player));
    }

    @Override
    public void add(int index, OfflinePlayer player) {
        add(index, PlayerRef.get(player));
    }

    @Override
    public boolean contains(OfflinePlayer player) {
        return contains(PlayerRef.get(player));
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
        return Iterables.transform(
                PlayersRefArrayList.this, new ToOfflinePlayer());
    }

    @Override
    public Iterable<Player> players() {
        return new PlayersIterable(PlayersRefArrayList.this);
    }

    @Override
    public List<OfflinePlayer> getOfflinePlayers() {
        return Lists.transform(this, new ToOfflinePlayer());
    }

    @Override
    public List<Player> getPlayers() {
        return Lists.newArrayList(players());
    }
}
