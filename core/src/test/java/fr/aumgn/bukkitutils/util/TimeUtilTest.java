package fr.aumgn.bukkitutils.util;

import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimeFormatException;
import fr.aumgn.bukkitutils.util.TimeUtil.UnknownTimePeriodException;

public class TimeUtilTest {

    private void assertTimeEquals(int expected, String timeStr) {
        int result = -1;
        try {
            result = TimeUtil.parseTime(timeStr);
        } catch (UnknownTimeFormatException e) {
            fail();
        } catch (UnknownTimePeriodException e) {
            fail();
        }

        assertEquals(expected, result);
    }

    @Test
    public void testParseTime() {
        assertTimeEquals(24000, "day");
        assertTimeEquals(38000, "night");
    }
}
