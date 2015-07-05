package fr.aumgn.bukkitutils.geom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Store informations of a position.
 * <p/>
 * Informations are :
 * <ul>
 * <li>World</li>
 * <li>Coordinates</li>
 * <li>Direction</li>
 * </ul>
 * <p/>
 * Contrary to {@link Location}, this class stores a
 * reference to the world rather than the world itself.
 */
public class Position {

    private UUID worldId;
    private Vector coord;
    private Direction direction;

    private transient WeakReference<World> worldRef = null;

    public Position(Location location) {
        setWorld(location.getWorld());
        setCoord(new Vector(location));
        setDirection(Directions.fromLocation(location));
    }

    public World getWorld() {
        World world = worldRef.get();
        if (world == null) {
            world = Bukkit.getWorld(worldId);
            worldRef = new WeakReference<World>(world);
        }

        return world;
    }

    public void setWorld(World world) {
        worldId = world.getUID();
        worldRef = new WeakReference<World>(world);
    }

    public Vector getCoord() {
        return coord;
    }

    private void setCoord(Vector vector) {
        coord = vector;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public Location toLocation() {
        return coord.toLocation(getWorld(), direction);
    }
}
