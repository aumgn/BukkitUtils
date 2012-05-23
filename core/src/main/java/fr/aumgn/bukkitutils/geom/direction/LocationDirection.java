package fr.aumgn.bukkitutils.geom.direction;

import org.bukkit.Location;

import fr.aumgn.bukkitutils.geom.Vector;

public class LocationDirection extends HorizontalDirection {

    public static double calculateVectorY(float yaw, float pitch) {
        return 0.0;
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
}
