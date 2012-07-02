package fr.aumgn.bukkitutils.geom;

import org.junit.Test;
import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void testBlockComponents() {
        Vector vector = new Vector(1.2, 10.5, 400.6);

        assertEquals(1, vector.getBlockX());
        assertEquals(11, vector.getBlockY());
        assertEquals(401, vector.getBlockZ());
    }

    @Test
    public void testEqualsBlock() {
        Vector vector1 = new Vector(1.2, 10.5, 400.6);
        Vector vector2 = new Vector(1.4, 10.9, 400.8);
        Vector vector3 = new Vector(1.1, 10.49, 400.9);

        assertFalse(vector1.equals(vector2));
        assertTrue(vector1.equalsBlock(vector2));
        assertFalse(vector1.equalsBlock(vector3));
    }

    @Test
    public void testGetMiddle() {
        Vector vector1 = new Vector(4, 10, 400);
        Vector vector2 = new Vector(7, 300, 500);

        Vector middle = vector1.getMiddle(vector2);

        assertEquals(new Vector(5.5, 155, 450), middle);
    }

    @Test
    public void testInside() {
        Vector min = new Vector(4, 10, 400);
        Vector max = new Vector(7, 300, 500);

        Vector vector1 = new Vector(5, 130, 500);
        Vector vector2 = new Vector(5, 130, 501);

        assertTrue(vector1.isInside(min, max));
        assertFalse(vector2.isInside(min, max));
    }

    @Test
    public void testPositive() {
        Vector vector = new Vector(-5, -130, 500);

        assertEquals(new Vector(5, 130, 500), vector.positive());
    }

    @Test
    public void testDistanceSq() {
        Vector vector1 = new Vector(4, 10, 400);
        Vector vector2 = new Vector(7, 300, 500);

        assertEquals(
                vector1.distanceSq(vector2),
                vector1.subtract(vector2).lengthSq(),
                0);
    }
}
