package fr.aumgn.bukkitutils.localization;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

public class LocalesLookUpTest {

    private void assertValidLookup(Locale targetLocale, Locale fallbackLocale,
            Locale... expected) {
        Locale[] actual = Localization.localesLookupFor(targetLocale,
                fallbackLocale);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testLocalesLookup1() {
        assertValidLookup(Locale.FRANCE, Locale.US,
                Locale.ENGLISH,
                Locale.US,
                Locale.FRENCH,
                Locale.FRANCE);
    }

    @Test
    public void testLocalesLookup2() {
        assertValidLookup(Locale.US, Locale.ENGLISH,
                Locale.ENGLISH,
                Locale.US);
    }

    @Test
    public void testLocalesLookup3() {
        assertValidLookup(Locale.US, Locale.US,
                Locale.ENGLISH,
                Locale.US);
    }

    @Test
    public void testLocalesLookup4() {
        assertValidLookup(Locale.FRENCH, Locale.FRENCH,
                Locale.FRENCH);
    }

    @Test
    public void testLocalesLookup5() {
        assertValidLookup(Locale.CANADA_FRENCH, Locale.CANADA,
                Locale.ENGLISH,
                Locale.CANADA,
                Locale.FRENCH,
                Locale.CANADA_FRENCH);
    }
}
