package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public class Vector2DDirection implements Direction {

    public static float calculateYaw(Vector2D vector) {
        if (vector.isZero()) {
            throw new UnsupportedOperationException(
                    vector.toString() + " is not a valid direction.");
        }

        double radians = Math.acos(vector.getZ() / vector.length());
        float yaw = (float) (radians * 180.0 / Math.PI);
        if (vector.getX() > 0) {
            yaw = 360.0f - yaw;
        }

        return yaw;
    }

    private final Vector2D vector;
    private float yaw;

    public Vector2DDirection(Vector2D vector) {
        this.vector = vector;
        this.yaw = Float.NaN;
    }

    @Override
    public float getYaw() {
        if (Float.isNaN(yaw)) {
            yaw = calculateYaw(vector);
        }

        return yaw;
    }

    @Override
    public float getPitch() {
        return 0.0f;
    }

    @Override
    public Vector2D getVector2D() {
        return vector;
    }

    @Override
    public Vector getVector() {
        return vector.to3D();
    }

    @Override
    public Direction rotate(float angle) {
        return HorizontalDirection.calculateHorizontalRotation(this, angle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(getYaw());
        result = prime * result + Float.floatToIntBits(getPitch());
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
        return vector.to3D().equals(other.getVector());
    }
}
