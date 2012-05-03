package fr.aumgn.bukkitutils.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VectorIterator implements Iterator<Vector> {

    private Vector min;
    private Vector max;
    private boolean hasNext;
    private double nextX;
    private double nextY;
    private double nextZ;

    public VectorIterator(Vector min, Vector max) {
        this.min = min;
        this.max = max;
        this.hasNext = true;
        this.nextX = min.getX();
        this.nextY = min.getY();
        this.nextZ = min.getZ();
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Vector next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }

        Vector answer = new Vector(nextX, nextY, nextZ);
        if (++nextX <= max.getX()) {
            return answer;
        }

        nextX = min.getX();
        if (++nextZ <= max.getZ()) {
            return answer;
        }

        nextZ = min.getZ();
        if (++nextY <= max.getY()) {
            return answer;
        }

        hasNext = false;
        return answer;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}