package fr.aumgn.bukkitutils.playerid;

import org.bukkit.OfflinePlayer;

import com.google.common.base.Function;

public class ToOfflinePlayer implements Function<PlayerId, OfflinePlayer> {
    @Override
    public OfflinePlayer apply(PlayerId playerId) {
        return playerId.getOfflinePlayer();
    }
}