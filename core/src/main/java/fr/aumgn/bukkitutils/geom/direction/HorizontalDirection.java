package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public class HorizontalDirection implements Direction {

    public static Vector2D calculateVector2D(float yaw) {
        double radians = (360.0f - yaw) * Math.PI / 180.0;
        return new Vector2D(
                Math.sin(radians), Math.cos(radians));
    }

    protected final float yaw;
    private Vector2D vector;

    public HorizontalDirection(float yaw) {
        this.yaw = yaw;
        this.vector = null;
    }

    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return 0.0f;
    }

    public Vector2D getVector2D() {
        if (vector == null) {
            vector = calculateVector2D(yaw);
        }

        return vector;
    }

    @Override
    public Vector getVector() {
        return new Vector();
    }
}
