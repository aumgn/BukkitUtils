package fr.aumgn.bukkitutils.playerref;

import com.google.common.base.Function;
import org.bukkit.OfflinePlayer;

public class ToOfflinePlayer implements Function<PlayerRef, OfflinePlayer> {
    @Override
    public OfflinePlayer apply(PlayerRef playerRef) {
        return playerRef.getOfflinePlayer();
    }
}