package fr.aumgn.bukkitutils.command.args;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import static fr.aumgn.bukkitutils.command.args.CommandArgsUtil.*;

public class CommandArgBaseTest {

    @Test
    public void testRemoveEmptyArgs() {
        CommandArgs args = parse(0, -1,
                "", "arg1", "", "arg2", "" );

        assertEquals(2, args.length());
        assertEquals("arg1", args.get(0));
        assertEquals("arg2", args.get(1));
    }

    @Test(expected = CommandUsageError.class)
    public void testArgsMinLength() {
        parse(1, 1);
    }

    @Test(expected = CommandUsageError.class)
    public void testArgsMaxLength() {
        parse(1, 1, "args1", "args2");
    }

    @Test
    public void testUnrestrictedArgsMaxLength() {
        CommandArgs args = parse(0, -1, "args");

        int size = 16;
        String[] tokens = new String[size];
        for (int i = 0; i < size; i++) {
            tokens[i] = "args" + (i + 1);
        }
        CommandArgs args2 = parse(0, -1, tokens);

        assertEquals(1, args.length());
        assertEquals(size, args2.length());
    }

    @Test
    public void testDoNotCountFlagsAsArgs() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');
        expectedFlags.add('d');
        CommandArgs args = parse(expectedFlags,
                "args1", "-td", "args2");

        assertEquals(2, args.length());
    }

    @Test
    public void testParseQuotedArgs() {
        CommandArgs args = parse(0, -1,
                "arg1 arg2 arg3".split(" "));
        CommandArgs args2 = parse(0, -1,
                "arg1 \"arg2\" arg3".split(" "));
        CommandArgs args3 = parse(0, -1,
                "arg1 \"arg2  with spaces\" arg3".split(" "));

        assertEquals(3, args.length());
        assertEquals("arg1", args.get(0));
        assertEquals("arg2", args.get(1));
        assertEquals("arg3", args.get(2));

        assertEquals(3, args2.length());
        assertEquals("arg1", args2.get(0));
        assertEquals("arg2", args2.get(1));
        assertEquals("arg3", args2.get(2));

        assertEquals(3, args3.length());
        assertEquals("arg1", args3.get(0));
        assertEquals("arg2  with spaces", args3.get(1));
        assertEquals("arg3", args3.get(2));
    }

    @Test(expected = CommandUsageError.class)
    public void testParseQuotedArgsWithoutEndingQuote() {
        parse(0, -1, "arg1", "\"arg2", "", "with", "spaces");
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidFlag() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');

        parse(expectedFlags, "args1", "-d", "args2");
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidArgFlag() {
        Set<Character> expectedArgFlags = new HashSet<Character>();
        expectedArgFlags.add('t');

        parseWithArgsFlags(expectedArgFlags, "args1", "-d=14", "args2");
    }

    @Test
    public void testFlagsPresence() {
        Set<Character> expectedFlags = new HashSet<Character>();
        Set<Character> expectedArgsFlags = new HashSet<Character>();
        expectedFlags.add('t');
        expectedArgsFlags.add('d');
        CommandArgs args = parse(expectedFlags, expectedArgsFlags, "args1", "args2");

        Set<Character> expectedFlags2 = new HashSet<Character>();
        expectedFlags2.add('t');
        expectedFlags2.add('d');
        expectedFlags2.add('c');
        expectedFlags2.add('f');
        expectedFlags2.add('p');
        CommandArgs args2 = parse(expectedFlags2,
                "-t", "args1", "-cd", "args2", "-f");

        assertFalse(args.hasFlags());
        assertFalse(args.hasFlag('t'));
        assertFalse(args.hasArgFlag('d'));

        assertTrue(args2.hasFlags());
        assertTrue(args2.hasFlag('t'));
        assertTrue(args2.hasFlag('d'));
        assertTrue(args2.hasFlag('c'));
        assertTrue(args2.hasFlag('f'));
        assertFalse(args2.hasFlag('p'));
    }

    @Test
    public void testArgFlags() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');
        expectedFlags.add('d');
        expectedFlags.add('p');
        CommandArgs args = parseWithArgsFlags(expectedFlags,
                "-t=Test", "-d=14", "args1", "args2");

        assertTrue(args.hasArgFlag('t'));
        assertEquals("Test", args.getArgFlag('t'));
        assertTrue(args.hasArgFlag('d'));
        assertEquals("14", args.getArgFlag('d'));
        assertFalse(args.hasFlag('p'));
    }

    @Test
    public void testArgsJoin() {
        CommandArgs args = parse("args", "to", "test");

        assertEquals("args to", args.get(0, 1));
        assertEquals("to test", args.get(1, 2));
        assertEquals("args to test", args.get(0, 2));
    }

    @Test
    public void testArgsJoinWithNegativeOne() {
        CommandArgs args = parse("args", "to", "test");

        assertEquals("args to test", args.get(0, -1));
        assertEquals("to test", args.get(1, -1));
    }

    @Test
    public void testAsList() {
        CommandArgs args = parse("args", "to", "test");
        List<String> list = args.asList();

        assertEquals(3, list.size());
        assertEquals("args", list.get(0));
        assertEquals("to", list.get(1));
        assertEquals("test", list.get(2));
    }

    @Test
    public void testAsSubList() {
        CommandArgs args = parse("args", "to", "test");
        List<String> list1 = args.asList(0, 1);
        List<String> list2 = args.asList(1);

        assertEquals(2, list1.size());
        assertEquals("args", list1.get(0));
        assertEquals("to", list1.get(1));

        assertEquals(2, list2.size());
        assertEquals("to", list2.get(0));
        assertEquals("test", list2.get(1));
    }

    @Test
    public void testAsSubListWithOutOfBoundIndex() {
        CommandArgs args = parse("args", "args2");
        List<String> list1 = args.asList(-4, 1);
        List<String> list2 = args.asList(0, 7);

        assertEquals(2, list1.size());
        assertEquals("args", list1.get(0));
        assertEquals("args2", list1.get(1));

        assertEquals(2, list2.size());
        assertEquals("args", list2.get(0));
        assertEquals("args2", list2.get(1));
    }
}