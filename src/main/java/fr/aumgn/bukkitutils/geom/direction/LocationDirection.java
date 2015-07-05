package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;
import org.bukkit.Location;

import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateVector;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateVector2D;

public final class LocationDirection extends AbstractDirection {

    private final float yaw;
    private final float pitch;
    private Vector vector;

    public LocationDirection(float yaw, float pitch) {
        this.yaw = yaw % 360;
        if (pitch < -180f || pitch > 180f) {
            throw new IllegalArgumentException("Invalid pitch");
        }
        this.pitch = pitch;
        this.vector = null;
    }

    public LocationDirection(Location location) {
        this(location.getYaw(), location.getPitch());
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public Vector2D getVector2D() {
        return getVector().to2D();
    }

    @Override
    public Vector getVector() {
        if (vector == null) {
            Vector2D vec2D = calculateVector2D(yaw);
            vector = calculateVector(vec2D, pitch);
        }

        return vector;
    }
}
