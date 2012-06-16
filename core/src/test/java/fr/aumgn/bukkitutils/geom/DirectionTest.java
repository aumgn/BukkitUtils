package fr.aumgn.bukkitutils.geom;

import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.geom.direction.HorizontalDirection;
import fr.aumgn.bukkitutils.geom.direction.LocationDirection;
import static fr.aumgn.bukkitutils.geom.VectorUtils.*;

public class DirectionTest {

    @Test
    public void testVector2DToYaw() {
        testVector2DToYaw(  0f,  0,  1);
        testVector2DToYaw( 45f, -1,  1);
        testVector2DToYaw( 90f, -1,  0);
        testVector2DToYaw(135f, -1, -1);
        testVector2DToYaw(180f,  0, -1);
        testVector2DToYaw(225f,  1, -1);
        testVector2DToYaw(270f,  1,  0);
        testVector2DToYaw(315f,  1,  1);
    }

    private void testVector2DToYaw(float yaw, int x, int z) {
        assertEquals(yaw, new Vector2D(x, z).toDirection().getYaw(), 0);
    }

    @Test
    public void testVectorToPitch() {
        testVectorToPitch(   0f,  1,  0);

        testVectorToPitch( 180f,  0,  1);
        testVectorToPitch(-180f,  0, -1);

        testVectorToPitch(  90f,  1,  1);
        testVectorToPitch( -90f,  1, -1);
    }

    private void testVectorToPitch(float pitch, int h, int y) {
        if (h == 0) {
            assertEquals(pitch, new Vector(0, y, 0).toDirection().getPitch(), 0);
        } else {
            assertEquals(pitch, new Vector(h, y, 0).toDirection().getPitch(), 0);
            assertEquals(pitch, new Vector(-h, y, 0).toDirection().getPitch(), 0);
            assertEquals(pitch, new Vector2D(h, h).normalize().to3D(y).toDirection().getPitch(), 0);
            assertEquals(pitch, new Vector2D(-h, h).normalize().to3D(y).toDirection().getPitch(), 0);
        }
    }

    @Test
    public void testYawToVector2D() {
        testYawToVector2D( 0,  1,   0f);
        testYawToVector2D(-1,  1,  45f);
        testYawToVector2D(-1,  0,  90f);
        testYawToVector2D(-1, -1, 135f);
        testYawToVector2D( 0, -1, 180f);
        testYawToVector2D( 1, -1, 225f);
        testYawToVector2D( 1,  0, 270f);
        testYawToVector2D( 1,  1, 315f);
    }

    private void testYawToVector2D(int x, int y, float yaw) {
        Vector2D expected = new Vector2D(x, y).normalize();
        Vector2D result = new HorizontalDirection(yaw).getVector2D();
        assertVector2DEquals(expected, result, 0.00001);
    }

    @Test
    public void testDirectionToVector() {
        testDirectionToVector( 0,  0,  1,   0f,   0f);
        testDirectionToVector( 1,  0,  0, 270f,   0f);

        testDirectionToVector( 0,  1,  1,   0f,  90f);
        testDirectionToVector( 0, -1,  1,   0f, -90f);

        testDirectionToVector( 1,  1,  0, 270f,  90f);
        testDirectionToVector( 1, -1,  0, 270f, -90f);
    }

    private void testDirectionToVector(int x,int y, int z,
            float yaw, float pitch) {
        Vector expected = new Vector(x, y, z).normalize();
        LocationDirection dir = new LocationDirection(yaw, pitch);
        assertVectorEquals(expected, dir.getVector(), 0.00001);
    }

    @Test
    public void testConversionConsistency() {
        testVectorConsistency(0.45, 4.65, 6.43);
        testVectorConsistency(67.4322, 504.65, 456.43);

        testYawPitchConsistency(0f, 90f);
        testYawPitchConsistency(180f, 0f);
        testYawPitchConsistency(90f, -90f);
}

    private void testVectorConsistency(double x, double y, double z) {
        Vector expected = new Vector(x, y, z).normalize();
        Direction vecDir = new Vector(x, y, z).toDirection();
        Direction dir = new LocationDirection(
                vecDir.getYaw(), vecDir.getPitch());

        assertVectorEquals(expected, dir.getVector(), 0.00001);
    }

    private void testYawPitchConsistency(float yaw, float pitch) {
        Direction dir = new LocationDirection(yaw, pitch);
        Direction vecDir = dir.getVector().toDirection();
        float actualYaw = vecDir.getYaw();
        float actualPitch = vecDir.getPitch();

        assertEquals(yaw, actualYaw, 0.00001);
        assertEquals(pitch, actualPitch, 0.00001);
    }

    @Test
    public void testEquality() {
        assertEquals(
                Faces.NORTH,
                new Vector2D(-1, 0).toDirection());
        assertEquals(
                Faces.UP_SOUTH,
                new Vector(1, 1, 0).toDirection());
        assertEquals(
                new HorizontalDirection(270f),
                new Vector2D(1, 0).toDirection());
        assertEquals(
                new LocationDirection(0f, 90f),
                new Vector(0, 1, 1).toDirection());
    }
}
