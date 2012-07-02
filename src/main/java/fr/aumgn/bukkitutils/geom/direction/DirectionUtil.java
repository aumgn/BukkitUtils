package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;

class DirectionUtil {

    public static float calculateYaw(Vector2D vector) {
        double radians = Math.acos(vector.getZ() / vector.length());
        float yaw = (float) (radians * 180f / Math.PI);
        if (vector.getX() > 0) {
            yaw = 360.0f - yaw;
        }

        return yaw;
    }

    public static float calculatePitch(Vector vector) {
        double radians = Math.asin(vector.getY() / vector.length());
        return (float) (radians * 360.0 / Math.PI);
    }

    public static Vector2D calculateVector2D(float yaw) {
        double radians = (360.0f - yaw) * Math.PI / 180.0;
        return new Vector2D(
                Math.sin(radians), Math.cos(radians));
    }

    public static Vector calculateVector(Vector2D vector2D, float pitch) {
        float angle = pitch / 2;
        double radians = angle * Math.PI / 180f;
        double y = Math.tan(radians);
        return vector2D.to3D(y).normalize();
    }

    public static Direction calculateRotation(Direction dir, float angle) {
        float oppositeYaw = (dir.getYaw() + 180f) % 360f;
        float oppositePitch = (dir.getPitch() + 180f + angle) % 360f - 180f;
        return new LocationDirection(oppositeYaw, oppositePitch);
    }

    public static Direction calculateHorizontalRotation(Direction dir, float angle) {
        float oppositeYaw = (dir.getYaw() + 180f) % 360f;
        return new HorizontalDirection(oppositeYaw);
    }



}
