package fr.aumgn.bukkitutils.geom.vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.aumgn.bukkitutils.geom.Vector2D;

public class Vector2DIterator implements Iterator<Vector2D> {

    private Vector2D min;
    private Vector2D max;
    private boolean hasNext;
    private double nextX;
    private double nextZ;

    public Vector2DIterator(Vector2D min, Vector2D max) {
        this.min = min;
        this.max = max;
        this.hasNext = true;
        this.nextX = min.getX();
        this.nextZ = min.getZ();
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Vector2D next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }

        Vector2D answer = new Vector2D(nextX, nextZ);
        if (++nextX <= max.getX()) {
            return answer;
        }

        nextX = min.getX();
        if (++nextZ <= max.getZ()) {
            return answer;
        }

        hasNext = false;
        return answer;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}