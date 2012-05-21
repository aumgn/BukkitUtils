package fr.aumgn.bukkitutils.playerid.iterator;

import java.util.Iterator;

import org.bukkit.OfflinePlayer;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public class OfflinePlayersIterator implements Iterator<OfflinePlayer> {

    private final Iterator<PlayerId> it;

    public OfflinePlayersIterator(Iterator<PlayerId> iterator) {
        this.it = iterator;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public OfflinePlayer next() {
        return it.next().getOfflinePlayer();
    }

    @Override
    public void remove() {
        it.remove();
    }
}
