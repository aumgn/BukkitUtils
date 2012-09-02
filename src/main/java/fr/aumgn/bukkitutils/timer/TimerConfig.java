package fr.aumgn.bukkitutils.timer;

/**
 * Configuration of a {@link Timer} class.
 */
public class TimerConfig {

    /**
     * Default Config
     */
    public static final TimerConfig DEFAULT = new TimerConfig(
            2 * 60, 20, "%02d:%02d", "Finished");

    private int majorDuration;
    private int minorDuration;
    private String format;
    private String endMessage;

    public TimerConfig(int majorDelay, int minorDelay, String format, String endMessage) {
        this.majorDuration = majorDelay;
        this.minorDuration = minorDelay;
        this.format = format;
        this.endMessage = endMessage;
    }

    public int getMajorDuration() {
        return majorDuration;
    }

    public TimerConfig setMajorDelay(int majorDelay) {
        this.majorDuration = majorDelay;
        return this;
    }

    public int getMinorDuration() {
        return minorDuration;
    }

    public TimerConfig setMinorDelay(int minorDelay) {
        this.minorDuration = minorDelay;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public TimerConfig setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public TimerConfig setEndMessage(String msg) {
        this.endMessage = msg;
        return this;
    }
}
