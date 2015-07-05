package fr.aumgn.bukkitutils.playerref.map;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import org.bukkit.OfflinePlayer;

import java.util.Map.Entry;

public class OfflinePlayerMapEntry<T> implements Entry<OfflinePlayer, T> {

    private final Entry<PlayerRef, T> entry;

    public OfflinePlayerMapEntry(Entry<PlayerRef, T> entry) {
        this.entry = entry;
    }

    @Override
    public OfflinePlayer getKey() {
        return entry.getKey().getOfflinePlayer();
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
