package fr.aumgn.bukkitutils.util;

import java.util.Iterator;

public class Vector2D implements Iterable<Vector2D> {

    private final int x, z;

    public Vector2D() {
        this.x = 0;
        this.z = 0;
    }

    public Vector2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public Vector2D setX(int x) {
        return new Vector2D(x, z);
    }

    public Vector2D setZ(int z) {
        return new Vector2D(x, z);
    }

    public Vector2D add(int i) {
        return new Vector2D(this.x + i, this.z + i);
    }

    public Vector2D add(int ox, int oz) {
        return new Vector2D(x + ox, z + oz);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, z + other.z);
    }

    public Vector2D subtract(int i) {
        return new Vector2D(x - i, z - i);
    }

    public Vector2D subtract(int ox, int oz) {
        return new Vector2D(x - ox, z - oz);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x - other.x, z - other.z);
    }

    public Vector2D getMiddle(Vector2D other) {
        return new Vector2D(
                (x + other.x) / 2,
                (z + other.z) / 2);
    }

    public boolean isInside(Vector2D min, Vector2D max) {
        return x >= min.x && x <= max.x
                && z >= min.z && z <= max.z;
    }

    public Vector2D positive() {
        return new Vector2D(Math.abs(x), Math.abs(z));
    }

    public int lengthSq() {
        return x * x + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSq());
    }

    public int distanceSq(Vector2D other) {
        return subtract(other).lengthSq();
    }

    public double distance(Vector2D other) {
        return subtract(other).length();
    }

    public float toYaw() {
        double radians = Math.acos(z / length());
        float yaw = (float) (radians * 180.0 / Math.PI);
        if (x > 0) {
            yaw += 180.0f;
            if (z > 0) {
                yaw += 90.0f;
            }
        }
        return yaw;
    }

    public Vector to3D() {
        return new Vector(x, 0, z);
    }

    public Vector to3D(int y) {
        return new Vector(x, y, z);
    }

    @Override
    public Iterator<Vector2D> iterator() {
        return new Vector2DIterator(new Vector2D(), this);
    }

    public Iterable<Vector2D> rectangle(final Vector2D max) {
        return new Iterable<Vector2D>() {
            @Override
            public Iterator<Vector2D> iterator() {
                return new Vector2DIterator(Vector2D.this, max);
            }
        };
    }

    @Override
    public String toString() {
        return "(" + x + ", " + + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += x;
        hash += z;
        return 37 * hash + 1;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2D))
            return false;
        Vector2D other = (Vector2D) obj;
        return (x == other.x && z == other.z);
    }
}
