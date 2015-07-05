package fr.aumgn.bukkitutils.geom;

import fr.aumgn.bukkitutils.geom.direction.VectorDirection;
import fr.aumgn.bukkitutils.geom.vector.VectorIterator;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.Iterator;

/**
 * Immutable Vector class.
 * Inspired from WorldEdit.
 */
public class Vector implements Iterable<Vector> {

    private final double x, y, z;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Location loc) {
        this(loc.getX(), loc.getY(), loc.getZ());
    }

    public Vector(Entity entity) {
        this(entity.getLocation());
    }

    public Vector(Block block) {
        this(block.getX(), block.getY(), block.getZ());
    }

    public double getX() {
        return x;
    }

    public Vector setX(double x) {
        return new Vector(x, y, z);
    }

    public int getBlockX() {
        return (int) Math.round(x);
    }

    public double getY() {
        return y;
    }

    public Vector setY(double y) {
        return new Vector(x, y, z);
    }

    public int getBlockY() {
        return (int) Math.round(y);
    }

    public double getZ() {
        return z;
    }

    public Vector setZ(double z) {
        return new Vector(x, y, z);
    }

    public int getBlockZ() {
        return (int) Math.round(z);
    }

    public Vector add(double i) {
        return new Vector(this.x + i, this.y + i, this.z + i);
    }

    public Vector add(double ox, double oy, double oz) {
        return new Vector(x + ox, y + oy, z + oz);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y, z + other.z);
    }

    public Vector addX(double ox) {
        return new Vector(x + ox, y, z);
    }

    public Vector addY(double oy) {
        return new Vector(x, y + oy, z);
    }

    public Vector addZ(double oz) {
        return new Vector(x, y, z + oz);
    }

    public Vector subtract(double i) {
        return new Vector(x - i, y - i, z - i);
    }

    public Vector subtract(double ox, double oy, double oz) {
        return new Vector(x - ox, y - oy, z - oz);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y, z - other.z);
    }

    public Vector subtractX(double ox) {
        return new Vector(x - ox, y, z);
    }

    public Vector subtractY(double oy) {
        return new Vector(x, y - oy, z);
    }

    public Vector subtractZ(double oz) {
        return new Vector(x, y, z - oz);
    }

    public Vector multiply(double i) {
        return new Vector(x * i, y * i, z * i);
    }

    public Vector multiply(double ox, double oy, double oz) {
        return new Vector(x * ox, y * oy, z * oz);
    }

    public Vector multiply(Vector other) {
        return new Vector(x * other.x, y * other.y, z * other.z);
    }

    public Vector divide(double i) {
        return new Vector(x / i, y / i, z / i);
    }

    public Vector divide(double ox, double oy, double oz) {
        return new Vector(x / ox, y / oy, z / oz);
    }

    public Vector divide(Vector other) {
        return new Vector(x / other.x, y / other.y, z / other.z);
    }

    public Vector getMiddle(Vector other) {
        return new Vector(
                (x + other.x) / 2,
                (y + other.y) / 2,
                (z + other.z) / 2);
    }

    public boolean isInside(Vector min, Vector max) {
        return x >= min.x && x <= max.x
                && y >= min.y && y <= max.y
                && z >= min.z && z <= max.z;
    }

    public boolean isZero() {
        return x == 0.0 && y == 0.0 && z == 0;
    }

    public Vector positive() {
        return new Vector(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public double lengthSq() {
        return x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSq());
    }

    public double distanceSq(Vector other) {
        return subtract(other).lengthSq();
    }

    public double distance(Vector other) {
        return subtract(other).length();
    }

    public Vector normalize() {
        return divide(length());
    }

    public Vector2D to2D() {
        return new Vector2D(x, z);
    }

    public Block toBlock(World world) {
        return world.getBlockAt(getBlockX(), getBlockY(), getBlockZ());
    }

    public Direction toDirection() {
        if (isZero()) {
            return Direction.NONE;
        }

        return new VectorDirection(this);
    }

    public Direction towards(Vector to) {
        return to.subtract(this).toDirection();
    }

    public org.bukkit.util.Vector toBukkit() {
        return new org.bukkit.util.Vector(x, y, z);
    }

    public Location toLocation(World world) {
        return toLocation(world, 0.0f, 0.0f);
    }

    public Location toLocation(World world, Vector2D direction) {
        return toLocation(world, direction.toDirection());
    }

    public Location toLocation(World world, Direction dir) {
        return toLocation(world, dir.getYaw(), dir.getPitch());
    }

    public Location toLocation(World world, float yaw, float pitch) {
        return new Location(world, x, getBlockY() + 0.1, z, yaw, pitch);
    }

    @Override
    public Iterator<Vector> iterator() {
        return new VectorIterator(new Vector(), this);
    }

    public Iterable<Vector> rectangle(final Vector max) {
        return new Iterable<Vector>() {
            @Override
            public Iterator<Vector> iterator() {
                return new VectorIterator(Vector.this, max);
            }
        };
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(23, 11)
                .append(x)
                .append(y)
                .append(z)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vector)) {
            return false;
        }

        Vector o = (Vector) obj;
        return x == o.x && y == o.y && z == o.z;
    }

    public boolean equalsBlock(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vector)) {
            return false;
        }

        Vector other = (Vector) obj;
        return getBlockX() == other.getBlockX()
                && getBlockY() == other.getBlockY()
                && getBlockZ() == other.getBlockZ();
    }
}
