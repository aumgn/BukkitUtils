package fr.aumgn.bukkitutils.geom;

/**
 * Represents a direction.
 */
public interface Direction {

    float getYaw();

    float getPitch();

    Vector2D getVector2D();

    Vector getVector();

    Direction rotate(float angle);

    public static final Direction NONE = new Direction() {
        @Override
        public Vector2D getVector2D() {
            return new Vector2D();
        }

        @Override
        public Vector getVector() {
            return new Vector();
        }

        @Override
        public float getYaw() {
            return Float.NaN;
        }

        @Override
        public float getPitch() {
            return Float.NaN;
        }

        @Override
        public Direction rotate(float angle) {
            return NONE;
        }

        @Override
        public int hashCode() {
            return 41;
        }
    };
}
