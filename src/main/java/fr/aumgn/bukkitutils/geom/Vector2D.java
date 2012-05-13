package fr.aumgn.bukkitutils.geom;

import java.util.Iterator;

public class Vector2D implements Iterable<Vector2D> {

    private final double x, z;

    public Vector2D() {
        this.x = 0;
        this.z = 0;
    }

    public Vector2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Vector2D(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public int getBlockX() {
        return (int) Math.round(x);
    }

    public double getZ() {
        return z;
    }

    public int getBlockZ() {
        return (int) Math.round(z);
    }

    public Vector2D setX(double x) {
        return new Vector2D(x, z);
    }

    public Vector2D setZ(double z) {
        return new Vector2D(x, z);
    }

    public Vector2D add(double i) {
        return new Vector2D(this.x + i, this.z + i);
    }

    public Vector2D add(double ox, double oz) {
        return new Vector2D(x + ox, z + oz);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, z + other.z);
    }

    public Vector2D addX(double ox) {
        return new Vector2D(x + ox, z);
    }

    public Vector2D addZ(double oz) {
        return new Vector2D(x, z + oz);
    }

    public Vector2D subtract(double i) {
        return new Vector2D(x - i, z - i);
    }

    public Vector2D subtract(double ox, double oz) {
        return new Vector2D(x - ox, z - oz);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x - other.x, z - other.z);
    }

    public Vector2D subtractX(double ox) {
        return new Vector2D(x - ox, z);
    }

    public Vector2D subtractZ(double oz) {
        return new Vector2D(x, z - oz);
    }

    public Vector2D multiply(double i) {
        return new Vector2D(x * i, z * i);
    }

    public Vector2D multiply(double ox, double oz) {
        return new Vector2D(x * ox, z * oz);
    }

    public Vector2D multiply(Vector2D other) {
        return new Vector2D(x * other.x, z * other.z);
    }

    public Vector2D divide(double i) {
        return new Vector2D(x / i, z / i);
    }

    public Vector2D divide(double ox, double oz) {
        return new Vector2D(x / ox, z / oz);
    }

    public Vector2D divide(Vector2D other) {
        return new Vector2D(x / other.x, z / other.z);
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

    public double lengthSq() {
        return x * x + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSq());
    }

    public double distanceSq(Vector2D other) {
        return subtract(other).lengthSq();
    }

    public double distance(Vector2D other) {
        return subtract(other).length();
    }

    public Vector2D rotate90() {
        return new Vector2D(-z, x);
    }

    public Vector2D normalize() {
        return divide(length());
    }

    public float toYaw() {
        double radians = Math.acos(z / length());
        float yaw = (float) (radians * 180.0 / Math.PI);
        if (x > 0) {
            yaw = 360.0f - yaw;
        }
        return yaw;
    }

    public Vector to3D() {
        return new Vector(x, 0, z);
    }

    public Vector to3D(double y) {
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
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2D other = (Vector2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
            return false;
        return true;
    }

    public boolean equalsBlock(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2D other = (Vector2D) obj;
        if (getBlockX() != other.getBlockX())
            return false;
        if (getBlockZ() != other.getBlockZ())
            return false;
        return true;
    }
}
