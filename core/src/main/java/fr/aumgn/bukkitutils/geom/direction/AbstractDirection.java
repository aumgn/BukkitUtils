package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateRotation;

public abstract class AbstractDirection implements Direction {

    @Override
    public Direction rotate(float angle) {
        return calculateRotation(this, angle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(getYaw());
        result = prime * result + Float.floatToIntBits(getPitch());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Direction)) {
            return false;
        }

        Direction other = (Direction) obj;
        if (getYaw() != other.getYaw()) {
            return false;
        }
        if (getPitch() != other.getPitch()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "(Direction : " + getYaw() + ", " + getPitch() + ")";
    }
}
