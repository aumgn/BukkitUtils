package fr.aumgn.bukkitutils.playerref;

import java.util.Iterator;

import org.bukkit.entity.Player;

import com.google.common.collect.AbstractIterator;

public class PlayersIterable implements Iterable<Player> {

    private static class PlayersIterator extends AbstractIterator<Player> {

        private final Iterator<PlayerRef> it;

        public PlayersIterator(Iterator<PlayerRef> iterator) {
            this.it = iterator;
        }

        protected Player computeNext() {
            while (it.hasNext()) {
                Player player = it.next().getPlayer();
                if (player != null) {
                    return player;
                }
            }

            return endOfData();
        }
    }

    private final PlayersIterator iterator;

    public PlayersIterable(Iterable<PlayerRef> iterable) {
        this.iterator = new PlayersIterator(iterable.iterator());
    }

    @Override
    public Iterator<Player> iterator() {
        return iterator;
    }
}
