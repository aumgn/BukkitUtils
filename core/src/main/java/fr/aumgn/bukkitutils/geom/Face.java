package fr.aumgn.bukkitutils.geom;

import fr.aumgn.bukkitutils.geom.direction.LocationDirection;

public final class Face implements Direction {

    public static final Face UP          = new Face(  0.0f,  180.0f,  0,  1,  0);
    public static final Face DOWN        = new Face(  0.0f, -180.0f,  0, -1,  0);

    public static final Face NORTH       = new Face( 90.0f,    0.0f, -1,  0,  0);
    public static final Face EAST        = new Face(180.0f,    0.0f,  0,  0, -1);
    public static final Face SOUTH       = new Face(270.0f,    0.0f,  1,  0,  0);
    public static final Face WEST        = new Face(  0.0f,    0.0f,  0,  0,  1);

    public static final Face UP_NORTH    = new Face( 90.0f,  180.0f, -1,  1,  0);
    public static final Face UP_EAST     = new Face(180.0f,  180.0f,  0,  1, -1);
    public static final Face UP_SOUTH    = new Face(270.0f,  180.0f,  1,  1,  0);
    public static final Face UP_WEST     = new Face(  0.0f,  180.0f,  0,  1,  1);

    public static final Face DOWN_NORTH  = new Face( 90.0f, -180.0f, -1, -1,  0);
    public static final Face DOWN_EAST   = new Face(180.0f, -180.0f,  0, -1, -1);
    public static final Face DOWN_SOUTH  = new Face(270.0f, -180.0f,  1, -1,  0);
    public static final Face DOWN_WEST   = new Face(  0.0f, -180.0f,  0, -1,  1);

    public static final Face NORTH_EAST  = new Face(135.0f,    0.0f, -1,  0, -1);
    public static final Face NORTH_WEST  = new Face( 45.0f,    0.0f, -1,  0,  1);

    public static final Face SOUTH_EAST  = new Face(225.0f,    0.0f,  1,  0, -1);
    public static final Face SOUTH_WEST  = new Face(315.0f,    0.0f,  1,  0,  1);

    private final float yaw;
    private final float pitch;
    private final int x;
    private final int y;
    private final int z;

    private Face(float yaw, float pitch, int x, int y, int z) {
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

    @Override
    public Direction rotate(float angle) {
        return new LocationDirection(yaw, pitch).rotate(angle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(yaw);
        result = prime * result + Float.floatToIntBits(pitch);
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
        if (pitch != other.getPitch()) {
            return false;
        }

        return true;
    }
}
