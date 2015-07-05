package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;
import org.apache.commons.lang.Validate;

public final class Face extends AbstractDirection {

    private static boolean done = false;
    private final float yaw;
    private final float pitch;
    private final Vector vector;
    public Face(float yaw, float pitch, int x, int y, int z) {
        Validate.isTrue(!done);
        this.yaw = yaw;
        this.pitch = pitch;
        this.vector = new Vector(x, y, z);
    }

    public static void done() {
        done = true;
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
        return vector.to2D();
    }

    @Override
    public Vector getVector() {
        return vector;
    }
}
