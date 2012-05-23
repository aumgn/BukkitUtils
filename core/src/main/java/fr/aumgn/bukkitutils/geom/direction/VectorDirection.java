package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public class VectorDirection implements Direction {

    public static float calculatePitch(Vector vector) {
        if (vector.isZero()) {
            throw new UnsupportedOperationException(
                    vector.toString() + " is not a valid direction.");
        }

        double radians = Math.asin(vector.getY() / vector.length());
        return (float) (radians * 360.0 / Math.PI);
    }

    private final Vector vector;
    private float yaw;
    private float pitch;

    public VectorDirection(Vector vector) {
        this.vector = vector;
        this.yaw = Float.NaN;
        this.pitch = Float.NaN;
    }

    @Override
    public float getYaw() {
        if (Float.isNaN(yaw)) {
            yaw = Vector2DDirection.calculateYaw(
                    vector.to2D());
        }

        return yaw;
    }

    @Override
    public float getPitch() {
        if (Float.isNaN(pitch)) {
            pitch = calculatePitch(vector);
        }

        return pitch;
    }

    @Override
    public Vector2D getVector2D() {
        return vector.to2D();
    }

    @Override
    public Vector getVector() {
        return vector;
    }

    @Override
    public Direction rotate(float angle) {
        return LocationDirection.calculateRotation(this, angle);
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
        return vector.equals(other.getVector());
    }
}
