package fr.aumgn.bukkitutils.geom.direction;

import fr.aumgn.bukkitutils.geom.Direction;
import org.apache.commons.lang.builder.HashCodeBuilder;

import static fr.aumgn.bukkitutils.geom.direction.DirectionUtil.calculateRotation;

abstract class AbstractDirection implements Direction {

    @Override
    public Direction rotate(float angle) {
        return calculateRotation(this, angle);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(41, 19)
                .append(getYaw())
                .append(getPitch())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbstractDirection)) {
            return false;
        }

        Direction other = (Direction) obj;
        return getYaw() == other.getYaw() && getPitch() == other.getPitch();

    }

    @Override
    public String toString() {
        return "(Direction : " + getYaw() + ", " + getPitch() + ")";
    }
}
