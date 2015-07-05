package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateHorizontalRotation;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateVector2D;

public final class HorizontalDirection extends AbstractDirection {

    protected final float yaw;
    private Vector2D vector;

    public HorizontalDirection(float yaw) {
        this.yaw = yaw % 360;
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
        return getVector2D().to3D();
    }

    @Override
    public Direction rotate(float angle) {
        return calculateHorizontalRotation(this, angle);
    }
}
