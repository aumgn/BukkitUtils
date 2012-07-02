package fr.aumgn.bukkitutils.geom;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import fr.aumgn.bukkitutils.geom.direction.HorizontalDirection;
import fr.aumgn.bukkitutils.geom.direction.LocationDirection;

public final class Directions {

    public static Direction fromLocation(Location location) {
        return new LocationDirection(location);
    }

    public static Direction fromHorizontalLocation(Location location) {
        return new HorizontalDirection(location.getYaw());
    }

    public static Direction fromEntity(Entity entity) {
        return fromLocation(entity.getLocation());
    }

    public static Direction fromPlayer(Entity entity) {
        return fromLocation(entity.getLocation());
    }

    public static Direction fromYawAndPitch(float yaw, float pitch) {
        return new LocationDirection(yaw, pitch);
    }

    public static Direction fromYaw(float yaw) {
        return new HorizontalDirection(yaw);
    }

    private Directions() {
    }
}
