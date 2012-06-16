package fr.aumgn.bukkitutils.glob;

import java.util.List;

import org.junit.Test;

import fr.aumgn.bukkitutils.glob.Glob;
import fr.aumgn.bukkitutils.glob.GlobPattern;
import fr.aumgn.bukkitutils.glob.exceptions.UnbalancedSquareBracketException;
import fr.aumgn.bukkitutils.glob.patterns.StringGlobPattern;

import static org.junit.Assert.*;

public class GlobTest {

    private static void assertMatch(String pattern, String string) {
        assertTrue(new Glob(pattern).build().match(string));
    }

    private static void assertDontMatch(String pattern, String string) {
        assertFalse(new Glob(pattern).build().match(string));
    }

    @Test
    public void testEmptyGlob() {
        assertMatch("", "");
        assertDontMatch("", "a");
    }

    @Test
    public void testTextGlob() {
        assertMatch("abc", "abc");
        assertMatch("abcdef", "abcdef");
        assertMatch("abcdefghijkl", "abcdefghijkl");

        assertDontMatch("abc", "abcdef");
        assertDontMatch("abcdefghijkl", "abc");
    }

    @Test
    public void testWildcard() {
        assertDontMatch("a*", "");
        assertMatch("a*", "a");
        assertMatch("a*", "aaa");
        assertMatch("a*", "aaaaaa");
    }

    @Test
    public void testAnyCharacter() {
        assertMatch("?", "a");
        assertDontMatch("?", "");
    }

    @Test
    public void testCharClass() {
        assertMatch("[ab]", "a");
        assertMatch("[ab]", "b");
        assertDontMatch("[ab]", "c");
    }

    @Test(expected = UnbalancedSquareBracketException.class)
    public void testInvalidCharClass() {
        new StringGlobPattern("[abcdef");
    }

    @Test
    public void testCharRange() {
        assertMatch("[a-c]", "a");
        assertMatch("[a-c]", "b");
        assertMatch("[a-c]", "c");
        assertDontMatch("[a-c]", "d");
    }

    @Test
    public void testInvertedCharRange() {
        assertMatch("[^]", "^");
        assertMatch("[a^]", "^");
        assertMatch("[a^]", "a");

        assertDontMatch("[^a]", "a");
        assertMatch("[^a]", "b");

        assertDontMatch("[^a-c]", "b");
        assertMatch("[^a-c]", "d");
    }

    @Test
    public void testInvalidCharRange() {
        StringGlobPattern pattern = new StringGlobPattern("[c-]");
        assertTrue(pattern.match("c"));
        assertTrue(pattern.match("-"));
        assertFalse(pattern.match("b"));
        assertFalse(pattern.match("d"));

        pattern = new StringGlobPattern("[-c]");
        assertTrue(pattern.match("c"));
        assertTrue(pattern.match("-"));
        assertFalse(pattern.match("b"));
        assertFalse(pattern.match("d"));
    }

    @Test
    public void testCharClassAndRange() {
        assertMatch("[bd-fh]", "b");
        assertDontMatch("[bd-fh]", "c");
        assertMatch("[bd-fh]", "e");
        assertDontMatch("[bd-fh]", "g");
        assertMatch("[bd-fh]", "h");
    }

    @Test
    public void testGlob() {
        assertMatch("aaa*c", "aaadfgshjc");
        assertDontMatch("aaa*c", "aaadfgshj");
        assertDontMatch("aaa*c", "aaadfgshjcd");
        assertMatch("aaa*c?", "aaadfgshjcd");

        assertMatch("aa*a[gd]c", "aaagc");
        assertDontMatch("aaa[fd]c", "aaagc");

        assertMatch("aaa?d", "aaagd");
        assertDontMatch("a*aa?sd", "aaagd");
    }

    @Test
    public void testGlobArray() {
        List<String> results =
                new Glob("abcd*i?kl[mno]").build().filter(
                        "abcdijklm", // Ok
                        "abcdefghiklm", // Missing character after i
                        "abdijklm", // Missing c
                        "abcdefghijklm", // Ok
                        "abcdijklmn"); // Extra n

        assertEquals(2, results.size());
        assertTrue(results.contains("abcdijklm"));
        assertTrue(results.contains("abcdefghijklm"));
    }

    @Test
    public void testExactGlob() {
        GlobPattern<String> glob =
                new Glob("a").exact().build();
        assertTrue  (glob.match("a"));
        assertFalse (glob.match("abc"));
        assertFalse (glob.match("cba"));
        assertFalse (glob.match("cbabc"));
    }

    @Test
    public void testStartGlob() {
        GlobPattern<String> glob =
                new Glob("a").start().build();

        assertTrue  (glob.match("a"));
        assertTrue  (glob.match("abc"));
        assertFalse (glob.match("cba"));
        assertFalse (glob.match("cbabc"));
    }

    @Test
    public void testPartialGlob() {
        GlobPattern<String> glob =
                new Glob("a").partial().build();

        assertTrue  (glob.match("a"));
        assertTrue  (glob.match("abc"));
        assertTrue  (glob.match("cba"));
        assertTrue  (glob.match("cbabc"));
    }

    @Test
    public void testCaseSensitiveGlob() {
        GlobPattern<String> glob =
                new Glob("a").cs().build();
        assertTrue  (glob.match("a"));
        assertFalse (glob.match("A"));
    }

    @Test
    public void testCaseInsensitiveGlob() {
        GlobPattern<String> glob =
                new Glob("a").ci().build();

        assertTrue  (glob.match("a"));
        assertTrue  (glob.match("A"));
    }
}
