package fr.aumgn.bukkitutils.geom.direction;

import org.bukkit.Location;

import fr.aumgn.bukkitutils.geom.Vector;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.*;

public class LocationDirection extends HorizontalDirection {

    private final float pitch;
    private Vector vector;

    public LocationDirection(float yaw, float pitch) {
        super(yaw);
        if (pitch <= -180f || pitch >= 180f) {
            throw new IllegalArgumentException("Invalid pitch");
        }
        this.pitch = pitch;
        this.vector = null;
    }

    public LocationDirection(Location location) {
        this(location.getYaw(), location.getPitch());
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public Vector getVector() {
        if (vector == null) {
            vector = calculateVector(getVector2D(), pitch);
        }

        return vector;
    }
}
