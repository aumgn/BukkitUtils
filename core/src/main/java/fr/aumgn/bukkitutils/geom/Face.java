package fr.aumgn.bukkitutils.geom;

public enum Face implements Direction {

    UP             (  0.0f,  180.0f,  0,  1,  0),
    DOWN           (  0.0f, -180.0f,  0, -1,  0),

    NORTH          ( 90.0f,    0.0f, -1,  0,  0),
    EAST           (180.0f,    0.0f,  0,  0, -1),
    SOUTH          (270.0f,    0.0f,  1,  0,  0),
    WEST           (  0.0f,    0.0f,  0,  0,  1),

    UP_NORTH       ( 90.0f,  180.0f, -1,  1,  0),
    UP_EAST        (180.0f,  180.0f,  0,  1, -1),
    UP_SOUTH       (270.0f,  180.0f,  1,  1,  0),
    UP_WEST        (  0.0f,  180.0f,  0,  1,  1),

    DOWN_NORTH     ( 90.0f, -180.0f, -1, -1,  0),
    DOWN_EAST      (180.0f, -180.0f,  0, -1, -1),
    DOWN_SOUTH     (270.0f, -180.0f,  1, -1,  0),
    DOWN_WEST      (  0.0f, -180.0f,  0, -1,  1),

    NORTH_EAST     (135.0f,    0.0f, -1,  0, -1),
    NORTH_WEST     ( 45.0f,    0.0f, -1,  0,  1),

    SOUTH_EAST     (225.0f,    0.0f,  1,  0, -1),
    SOUTH_WEST     (315.0f,    0.0f,  1,  0,  1);

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
}
