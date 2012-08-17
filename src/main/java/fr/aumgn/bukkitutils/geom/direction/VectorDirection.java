package fr.aumgn.bukkitutils.geom.direction;

import static com.google.common.base.Preconditions.checkArgument;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculatePitch;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateYaw;
import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public final class VectorDirection extends AbstractDirection
        implements Direction {

    private final Vector vector;
    private float yaw;
    private float pitch;

    public VectorDirection(Vector vector) {
        checkArgument(!vector.isZero(),
                "Cannot create a direction with a null vector.");
        this.vector = vector.normalize();
        this.yaw = Float.NaN;
        this.pitch = Float.NaN;
    }

    @Override
    public float getYaw() {
        if (Float.isNaN(yaw)) {
            yaw = calculateYaw(vector.to2D());
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
    public boolean equals(Object obj) {
        if (obj instanceof VectorDirection) {
            return vector.equals(((VectorDirection) obj).vector);
        }

        return super.equals(obj);
    }
}
