package fr.aumgn.bukkitutils.playerid;

import java.util.Iterator;

import org.bukkit.entity.Player;

import com.google.common.collect.AbstractIterator;

public class PlayersIterable implements Iterable<Player> {

    private static class PlayersIterator extends AbstractIterator<Player> {

        private final Iterator<PlayerId> it;

        public PlayersIterator(Iterator<PlayerId> iterator) {
            this.it = iterator;
        }

        protected Player computeNext() {
            while (it.hasNext()) {
                PlayerId playerId = it.next();
                if (playerId.isOnline()) {
                    return playerId.getPlayer();
                }
            }

            return endOfData();
        }
    }

    private final PlayersIterator iterator;

    public PlayersIterable(Iterable<PlayerId> iterable) {
        this.iterator = new PlayersIterator(iterable.iterator());
    }

    @Override
    public Iterator<Player> iterator() {
        return iterator;
    }
}
