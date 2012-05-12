package fr.aumgn.bukkitutils.commands;

import java.util.Collections;
import java.util.Set;

import org.bukkit.Material;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.CommandArgs;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.exception.InvalidMaterialAndDataFormat;
import fr.aumgn.bukkitutils.command.exception.NoSuchMaterial;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.util.MaterialAndData;

public class CommandArgsTest {

    private Messages local;
    private Set<Character> expectedFlags;

    public CommandArgsTest() {
        this.local = new Messages();
        this.expectedFlags =
                Collections.<Character>emptySet();
    }

    private CommandArgs parseArgs(String... tokens) {
        return new CommandArgs(local, tokens,
                expectedFlags, tokens.length, tokens.length);
    }

    @Test
    public void testIntegerArgs() {
        CommandArgs args = parseArgs("arg1", "1");

        assertEquals(1, args.getInteger(1));
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidIntegerArgs() {
        CommandArgs args = parseArgs("arg1", "arg2");

        args.getInteger(1);
    }

    @Test
    public void testDoubleArgs() {
        CommandArgs args = parseArgs("arg1", "1.0");

        assertEquals(1.0, args.getDouble(1), 0);
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidDoubleArgs() {
        CommandArgs args = parseArgs("arg1", "args2");

        args.getDouble(1);
    }

    @Test
    public void testMaterial() {
        CommandArgs args = parseArgs("stone",
                String.valueOf(Material.STONE.getId()));

        assertEquals(Material.STONE, args.getMaterial(0));
        assertEquals(Material.STONE, args.getMaterial(1));
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterial() {
        CommandArgs args = parseArgs(
                "nomaterialshouldeverhavethisname");

        args.getMaterial(0);
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterialId() {
        CommandArgs args = parseArgs(
                String.valueOf(Integer.MAX_VALUE));

        args.getMaterial(0);
    }

    @Test
    public void testMaterialAndData() {
        CommandArgs args = parseArgs("stone:3");
        MaterialAndData materialAndData = args.getMaterialAndData(0);

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(3, materialAndData.getData());
    }

    @Test
    public void testMaterialAndDataWithoutData() {
        CommandArgs args = parseArgs("stone");
        MaterialAndData materialAndData = args.getMaterialAndData(0);

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(0, materialAndData.getData());
    }

    @Test(expected = InvalidMaterialAndDataFormat.class)
    public void testInvalidMaterialAndDataFormat() {
        CommandArgs args = parseArgs("stone:4:5");

        args.getMaterialAndData(0);
    }

    @Test(expected = InvalidMaterialAndDataFormat.class)
    public void testInvalidMaterialAndDataData() {
        CommandArgs args = parseArgs("stone:invaliddata");

        args.getMaterialAndData(0);
    }
}
