package fr.aumgn.bukkitutils.playerid.map;

import java.util.Map.Entry;

import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.playerid.PlayerId;

public class PlayerMapEntry<T> implements Entry<Player, T> {

    private final Entry<PlayerId, T> entry;

    public PlayerMapEntry(Entry<PlayerId, T> entry) {
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
