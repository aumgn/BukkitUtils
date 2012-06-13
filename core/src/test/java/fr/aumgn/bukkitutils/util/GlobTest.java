package fr.aumgn.bukkitutils.util;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class GlobTest {

    private static void assertMatch(String pattern, String string) {
        assertTrue(Glob.match(pattern, string));
    }

    private static void assertDontMatch(String pattern, String string) {
        assertFalse(Glob.match(pattern, string));
    }

    @Test
    public void testEmptyGlob() {
        assertMatch      ("", "");
        assertDontMatch  ("", "a");
    }

    @Test
    public void testTextGlob() {
        assertMatch      ("abc", "abc");
        assertMatch      ("abcdef", "abcdef");
        assertMatch      ("abcdefghijkl", "abcdefghijkl");

        assertDontMatch  ("abc", "abcdef");
        assertDontMatch  ("abcdefghijkl", "abc");
    }

    @Test
    public void testWildcard() {
        assertMatch      ("*", "");
        assertMatch      ("*", "a");
        assertMatch      ("*", "aaa");
        assertMatch      ("*", "aaaaaa");
    }

    @Test
    public void testAnyCharacter() {
        assertMatch      ("?", "a");
        assertDontMatch  ("?", "");
    }

    @Test
    public void testCharClass() {
        assertMatch      ("[ab]", "a");
        assertMatch      ("[ab]", "b");
        assertDontMatch  ("[ab]", "c");
    }

    @Test
    public void testCharRange() {
        assertMatch      ("[a-c]", "a");
        assertMatch      ("[a-c]", "b");
        assertMatch      ("[a-c]", "c");
        assertDontMatch  ("[a-c]", "d");
    }

    @Test
    public void testCharClassAndRange() {
        assertMatch      ("[bd-fh]", "b");
        assertDontMatch  ("[bd-fh]", "c");
        assertMatch      ("[bd-fh]", "e");
        assertDontMatch  ("[bd-fh]", "g");
        assertMatch      ("[bd-fh]", "h");
    }

    @Test
    public void testGlob() {
        assertMatch      ("aaa*c",     "aaadfgshjc");
        assertDontMatch  ("aaa*c",     "aaadfgshj");
        assertDontMatch  ("aaa*c",     "aaadfgshjcd");
        assertMatch      ("aaa*c?",     "aaadfgshjcd");

        assertMatch      ("aa*a[gd]c",  "aaagc");
        assertDontMatch  ("aaa[fd]c",  "aaagc");

        assertMatch      ("aaa?d",     "aaagd");
        assertDontMatch  ("a*aa?sd",    "aaagd");
    }

    @Test
    public void testGlobArray() {
        List<String> results = Glob.match("abcd*i?kl[mno]",
                "abcdijklm", // Ok
                "abcdefghiklm", // Missing character after i
                "abdijklm", // Missing c 
                "abcdefghijklm", // Ok
                "abcdijklmn"); // Extra n 

        assertEquals(2, results.size());
        assertTrue(results.contains("abcdijklm"));
        assertTrue(results.contains("abcdefghijklm"));
    }
}
