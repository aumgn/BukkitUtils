package fr.aumgn.bukkitutils.playerid.map;

import java.util.Map.Entry;

import org.bukkit.OfflinePlayer;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public class OfflinePlayerMapEntry<T> implements Entry<OfflinePlayer, T> {

    private final Entry<PlayerId, T> entry;

    public OfflinePlayerMapEntry(Entry<PlayerId, T> entry) {
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
