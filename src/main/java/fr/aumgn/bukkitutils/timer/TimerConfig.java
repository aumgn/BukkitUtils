package fr.aumgn.bukkitutils.timer;

public class TimerConfig {

    public final static TimerConfig DEFAULT = new TimerConfig(
            2 * 60, 20, "%02d:%02d");

    private int majorDuration;
    private int minorDuration;
    private String format;

    public TimerConfig(int majorDelay, int minorDelay, String format) {
        this.majorDuration = majorDelay;
        this.minorDuration = minorDelay;
        this.format = format;
    }

    public int getMajorDuration() {
        return majorDuration;
    }

    public void setMajorDelay(int majorDelay) {
        this.majorDuration = majorDelay;
    }

    public int getMinorDuration() {
        return minorDuration;
    }

    public void setMinorDelay(int minorDelay) {
        this.minorDuration = minorDelay;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
