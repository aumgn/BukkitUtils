package fr.aumgn.bukkitutils.geom.direction;

import org.apache.commons.lang.Validate;

import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

public final class Face extends AbstractDirection {

    private static boolean done = false;

    public static void done() {
        done = true;
    }

    private final float yaw;
    private final float pitch;
    private final int x;
    private final int y;
    private final int z;

    public Face(float yaw, float pitch, int x, int y, int z) {
        Validate.isTrue(!done);
        this.yaw = yaw;
        this.pitch = pitch;
        this.x = x;
        this.y = y;
        this.z = z;
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
        return new Vector2D(x, z);
    }

    @Override
    public Vector getVector() {
        return new Vector(x, y, z);
    }
}
