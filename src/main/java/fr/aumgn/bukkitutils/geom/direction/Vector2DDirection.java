package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

import static com.google.common.base.Preconditions.checkArgument;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateHorizontalRotation;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateYaw;

public final class Vector2DDirection extends AbstractDirection
        implements Direction {

    private final Vector2D vector;
    private float yaw;

    public Vector2DDirection(Vector2D vector) {
        checkArgument(!vector.isZero(),
                "Cannot create a direction with a null vector.");
        this.vector = vector.normalize();
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
        return calculateHorizontalRotation(this, angle);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2DDirection) {
            return vector.equals(((Vector2DDirection) obj).vector);
        }

        return super.equals(obj);
    }
}
