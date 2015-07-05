package fr.aumgn.bukkitutils.playerref.map;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.entity.Player;

import java.util.Map.Entry;

public class PlayerMapEntry<T> implements Entry<Player, T> {

    private final Entry<PlayerRef, T> entry;

    public PlayerMapEntry(Entry<PlayerRef, T> entry) {
        this.entry = entry;
    }

    @Override
    public Player getKey() {
        return entry.getKey().getPlayer();
    }

    @Override
    public T getValue() {
        return entry.getValue();
    }

    @Override
    public T setValue(T value) {
        return entry.setValue(value);
    }
}
