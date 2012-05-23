package fr.aumgn.bukkitutils.geom.direction;

import org.bukkit.Location;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;

public class LocationDirection extends HorizontalDirection {

    public static double calculateVectorY(float yaw, float pitch) {
        return 0.0;
    }

    public static Direction calculateRotation(Direction dir, float angle) {
        float oppositeYaw = (dir.getYaw() + 180f) % 360f;
        float oppositePitch = (dir.getPitch() + 180f + angle) % 360f - 180f;
        return new LocationDirection(oppositeYaw, oppositePitch);
    }

    private final float pitch;
    private double y;

    public LocationDirection(float yaw, float pitch) {
        super(yaw);
        this.pitch = Float.NaN;
    }

    public LocationDirection(Location location) {
        this(location.getYaw(), location.getPitch());
    }

    public float getPitch() {
        return pitch;
    }

    public double getVectorY() {
        if (Double.isNaN(y)) {
            y = calculateVectorY(yaw, pitch);
        }

        return y;
    }

    public Vector getVector() {
        return getVector2D().to3D(getVectorY());
    }

    @Override
    public Direction rotate(float angle) {
        return calculateRotation(this, angle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(yaw);
        result = prime * result + Float.floatToIntBits(pitch);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Direction)) {
            return false;
        }

        Direction other = (Direction) obj;
        if (yaw != other.getYaw()) {
            return false;
        }
        if (pitch != other.getPitch()) {
            return false;
        }

        return true;
    }
}
