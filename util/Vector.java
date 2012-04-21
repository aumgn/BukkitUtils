package fr.aumgn.bukkitutils.util;

import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Vector implements Iterable<Vector> {

    private final int x, y, z;

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

    public Vector(Location loc) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public Vector(Block block) {
        this(block.getX(), block.getY(), block.getZ());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Vector setX(int x) {
        return new Vector(x, y, z);
    }

    public Vector setY(int y) {
        return new Vector(x, y, z);
    }

    public Vector setZ(int z) {
        return new Vector(x, y, z);
    }

    public Vector add(int i) {
        return new Vector(this.x + i, this.y + i, this.z + i);
    }

    public Vector add(int ox, int oy, int oz) {
        return new Vector(x + ox, y + oy, z + oz);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y, z + other.z);
    }

    public Vector subtract(int i) {
        return new Vector(x - i, y - i, z - i);
    }

    public Vector subtract(int ox, int oy, int oz) {
        return new Vector(x - ox, y - oy, z - oz);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y, z - other.z);
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

    public Vector positive() {
        return new Vector(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public int lengthSq() {
        return x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSq());
    }

    public int distanceSq(Vector other) {
        return subtract(other).lengthSq();
    }

    public double distance(Vector other) {
        return subtract(other).length();
    }

    public Vector2D to2D() {
        return new Vector2D(x, z);
    }

    public Block toBlock(World world) {
        return world.getBlockAt(x, y, z);
    }

    public Location toLocation(World world) {
        return new Location(world, x + 0.5, y, 
                z + 0.5, 0.0f, 0.0f);
    }

    public Location toLocation(World world, Vector2D direction) {
        return new Location(world, x + 0.5, y, 
                z + 0.5, direction.toYaw(), 0.0f);
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
        int hash = 0;
        hash += x;
        hash += y;
        hash += z;
        return 31 * hash + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) obj;
        return (x == other.x && y == other.y 
                && z == other.z);
    }
}
