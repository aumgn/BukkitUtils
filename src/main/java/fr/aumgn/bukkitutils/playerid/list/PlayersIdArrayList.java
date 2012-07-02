package fr.aumgn.bukkitutils.playerid.list;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.aumgn.bukkitutils.playerid.PlayerId;
import fr.aumgn.bukkitutils.playerid.PlayersIterable;
import fr.aumgn.bukkitutils.playerid.ToOfflinePlayer;

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
        return Iterables.transform(
                PlayersIdArrayList.this, new ToOfflinePlayer());
    }

    @Override
    public Iterable<Player> players() {
        return new PlayersIterable(PlayersIdArrayList.this);
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
