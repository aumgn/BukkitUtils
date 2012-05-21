package fr.aumgn.bukkitutils.command;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.CommandArgsBase;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandArgBaseTest {

    private Messages local;

    public CommandArgBaseTest() {
        this.local = new Messages();
    }

    @Test
    public void testRemoveEmptyWhitespace() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "", "arg1", "", "arg2", "" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);

        assertEquals(2, args.length());
        assertEquals("arg1", args.get(0));
        assertEquals("arg2", args.get(1));
    }

    @Test(expected = CommandUsageError.class)
    public void testArgsMinLength() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = {};
        new CommandArgsBase(
                local, tokens, expectedFlags, 1, 1);
    }

    @Test(expected = CommandUsageError.class)
    public void testArgsMaxLength() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args1", "args2" };
        new CommandArgsBase(
                local, tokens, expectedFlags, 1, 1);
    }

    @Test
    public void testUnrestrictedArgsMaxLength() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);

        int size = 16;
        String[] tokens2 = new String[size];
        for (int i = 0; i < size; i++) {
            tokens2[i] = "args" + (i + 1);
        }
        CommandArgsBase args2 = new CommandArgsBase(
                local, tokens2, expectedFlags, 0, -1);

        assertEquals(1, args.length());
        assertEquals(size, args2.length());
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidFlag() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');
        String[] tokens = { "args1", "-d", "args2" };
        new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);
    }

    @Test
    public void testFlagsPresence() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');
        String[] tokens = { "args1", "args2" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);

        Set<Character> expectedFlags2 = new HashSet<Character>();
        expectedFlags2.add('t');
        expectedFlags2.add('d');
        expectedFlags2.add('c');
        expectedFlags2.add('f');
        expectedFlags2.add('p');
        String[] tokens2 = { "-t", "args1", "-cd", "args2", "-f" };
        CommandArgsBase args2 = new CommandArgsBase(
                local, tokens2, expectedFlags2, 0, -1);

        assertFalse(args.hasFlags());
        assertFalse(args.hasFlag('t'));

        assertTrue(args2.hasFlags());
        assertTrue(args2.hasFlag('t'));
        assertTrue(args2.hasFlag('d'));
        assertTrue(args2.hasFlag('c'));
        assertTrue(args2.hasFlag('f'));
        assertFalse(args2.hasFlag('p'));
    }

    @Test
    public void testDoNotCountFlagsAsArgs() {
        Set<Character> expectedFlags = new HashSet<Character>();
        expectedFlags.add('t');
        expectedFlags.add('d');
        String[] tokens = { "args1", "-td", "args2" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);

        assertEquals(2, args.length());
    }

    @Test
    public void testArgsJoin() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args", "to", "test" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);

        assertEquals("args to", args.get(0, 1));
        assertEquals("to test", args.get(1, 2));
        assertEquals("args to test", args.get(0, 2));
    }

    @Test
    public void testAsList() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args", "to", "test" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);
        List<String> list = args.asList();

        assertEquals(3, list.size());
        assertEquals("args", list.get(0));
        assertEquals("to", list.get(1));
        assertEquals("test", list.get(2));
    }

    @Test
    public void testAsSubList() {
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args", "to", "test" };
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);
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
        Set<Character> expectedFlags = Collections.<Character>emptySet();
        String[] tokens = { "args", "args2"};
        CommandArgsBase args = new CommandArgsBase(
                local, tokens, expectedFlags, 0, -1);
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