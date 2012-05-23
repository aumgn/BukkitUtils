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

    public static Direction calculateHorizontalRotation(Direction dir, float angle) {
        float oppositeYaw = (dir.getYaw() + 180f) % 360f;
        return new HorizontalDirection(oppositeYaw);
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

    @Override
    public Direction rotate(float angle) {
        return calculateHorizontalRotation(this, angle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(yaw);
        result = prime * result;
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
        if (0f != other.getPitch()) {
            return false;
        }

        return true;
    }
}
