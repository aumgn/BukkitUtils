package fr.aumgn.bukkitutils.util;

/**
 * {@link java.util.Random} subclass which add convenient methods.
 */
public class Random extends java.util.Random {
    private static final long serialVersionUID = 7538154847422151091L;

    public int nextInt(int min, int max) {
        if (min < max) {
            return min + nextInt(max - min);
        }

        return min;
    }

    public double nextDouble(double max) {
        return nextDouble() * max;
    }

    public double nextDouble(double min, double max) {
        if (min < max) {
            return min + nextDouble(max - min);
        }

        return min;
    }
}
