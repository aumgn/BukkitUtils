package fr.aumgn.bukkitutils.util;

public class TimerConfig {

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
