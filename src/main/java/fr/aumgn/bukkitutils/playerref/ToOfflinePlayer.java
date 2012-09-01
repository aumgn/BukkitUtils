package fr.aumgn.bukkitutils.playerref;

import org.bukkit.OfflinePlayer;

import com.google.common.base.Function;

public class ToOfflinePlayer implements Function<PlayerRef, OfflinePlayer> {
    @Override
    public OfflinePlayer apply(PlayerRef playerRef) {
        return playerRef.getOfflinePlayer();
    }
}