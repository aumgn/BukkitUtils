package fr.aumgn.bukkitutils.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Time parser extracted from CommandBook
 * Credits go to sk89q and contributors.
 */
public final class TimeUtil {

    protected static final Pattern TWELVE_HOUR_TIME =
            Pattern.compile("^([0-9]+(?::[0-9]+)?)([apmAPM\\.]+)$");

    private TimeUtil() {
    }

    /**
     * Parse a time string
     * Accepted format are:
     * <ul>
     * <li>0 to 24 numbers for hour</li>
     * <li>Numbers above 24 for ticks</li>
     * <li>hh:mm (24h format)</li>
     * <li>hh:mm{am,pm,a.m.,p.m.} (12h format)</li>
     * <li>
     * shortcuts :
     * <ul>
     * <li>dawn</li>
     * <li>sunrise</li>
     * <li>morning</li>
     * <li>day</li>
     * <li>midday</li>
     * <li>noon</li>
     * <li>afternoon</li>
     * <li>evening</li>
     * <li>sunset</li>
     * <li>dusk</li>
     * <li>night</li>
     * <li>midnight</li>
     * </ul>
     * </li>
     * </ul>
     */
    public static int parseTime(String timeStr)
            throws UnknownTimeFormatException, UnknownTimePeriodException {
        Matcher matcher;

        try {
            int time = Integer.parseInt(timeStr);

            // People tend to enter just a number of the hour
            if (time <= 24) {
                return ((time - 8) % 24) * 1000;
            }

            return time;
        }
        catch (NumberFormatException exc) {
            // Not an integer!
        }

        // Tick time
        if (timeStr.matches("^*[0-9]+$")) {
            return Integer.parseInt(timeStr.substring(1));

            // Allow 24-hour time
        }
        else if (timeStr.matches("^[0-9]+:[0-9]+$")) {
            String[] parts = timeStr.split(":");
            int hours = Integer.parseInt(parts[0]);
            int mins = Integer.parseInt(parts[1]);
            return (int) (((hours - 8) % 24) * 1000
                    + Math.round((mins % 60) / 60.0 * 1000));

            // Or perhaps 12-hour time
        }
        else if ((matcher = TWELVE_HOUR_TIME.matcher(timeStr)).matches()) {
            String time = matcher.group(1);
            String period = matcher.group(2);
            int shift;

            if (period.equalsIgnoreCase("am")
                    || period.equalsIgnoreCase("a.m.")) {
                shift = 0;
            }
            else if (period.equalsIgnoreCase("pm")
                    || period.equalsIgnoreCase("p.m.")) {
                shift = 12;
            }
            else {
                throw new UnknownTimePeriodException(period);
            }

            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int mins = parts.length >= 2 ? Integer.parseInt(parts[1]) : 0;
            return (int) ((((hours % 12) + shift - 8) % 24) * 1000
                    + (mins % 60) / 60.0 * 1000);

            // Or some shortcuts
        }
        else if (timeStr.equalsIgnoreCase("dawn")) {
            return (6 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("sunrise")) {
            return (7 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("morning")) {
            return (24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("day")) {
            return (24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("midday")
                || timeStr.equalsIgnoreCase("noon")) {
            return (12 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("afternoon")) {
            return (14 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("evening")) {
            return (16 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("sunset")) {
            return (21 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("dusk")) {
            return (21 - 8 + 24) * 1000 + (int) (30 / 60.0 * 1000);
        }
        else if (timeStr.equalsIgnoreCase("night")) {
            return (22 - 8 + 24) * 1000;
        }
        else if (timeStr.equalsIgnoreCase("midnight")) {
            return (0 - 8 + 24) * 1000;
        }

        throw new UnknownTimeFormatException(timeStr);
    }

    public static class UnknownTimeFormatException extends Exception {
        private static final long serialVersionUID = -3382490815044549013L;

        private final String time;

        public UnknownTimeFormatException(String time) {
            super("Unrecognized format \"" + time + "\".");
            this.time = time;
        }

        public String getTime() {
            return time;
        }
    }

    public static class UnknownTimePeriodException extends Exception {
        private static final long serialVersionUID = -1749578413958028355L;

        private final String period;

        public UnknownTimePeriodException(String period) {
            super("'am' or 'pm' expected, got '" + period + "'.");
            this.period = period;
        }

        public String getPeriod() {
            return period;
        }
    }
}
