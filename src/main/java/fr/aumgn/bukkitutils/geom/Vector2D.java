package fr.aumgn.bukkitutils.geom;

import fr.aumgn.bukkitutils.geom.direction.Vector2DDirection;
import fr.aumgn.bukkitutils.geom.vector.Vector2DIterator;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.Iterator;

/**
 * Immutable Vector2D class.
 * Inspired from WorldEdit.
 */
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

    public Vector2D(Location loc) {
        this(loc.getX(), loc.getZ());
    }

    public Vector2D(Entity entity) {
        this(entity.getLocation());
    }

    public Vector2D(Block block) {
        this(block.getX(), block.getZ());
    }

    public double getX() {
        return x;
    }

    public Vector2D setX(double x) {
        return new Vector2D(x, z);
    }

    public int getBlockX() {
        return (int) Math.round(x);
    }

    public double getZ() {
        return z;
    }

    public Vector2D setZ(double z) {
        return new Vector2D(x, z);
    }

    public int getBlockZ() {
        return (int) Math.round(z);
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

    public boolean isZero() {
        return x == 0.0 && z == 0;
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

    public Direction toDirection() {
        if (isZero()) {
            return Direction.NONE;
        }

        return new Vector2DDirection(this);
    }

    public Direction towards(Vector2D to) {
        return to.subtract(this).toDirection();
    }

    public Vector to3D() {
        return new Vector(x, 0, z);
    }

    public Vector to3D(double y) {
        return new Vector(x, y, z);
    }

    public Vector toHighest(World world) {
        int y = world.getHighestBlockYAt(
                getBlockX(), getBlockZ());
        return to3D(y);
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
        return "(" + x + ", " + +z + ")";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(29, 13)
                .append(x)
                .append(z)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vector2D)) {
            return false;
        }

        Vector2D other = (Vector2D) obj;
        return x == other.x && z == other.z;

    }

    public boolean equalsBlock(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vector2D)) {
            return false;
        }

        Vector2D other = (Vector2D) obj;
        return getBlockX() == other.getBlockX() && getBlockZ() == other.getBlockZ();

    }
}
