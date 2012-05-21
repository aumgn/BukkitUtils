package fr.aumgn.bukkitutils.command;

import org.bukkit.Material;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.CommandArgs;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.exception.InvalidMaterialAndDataFormat;
import fr.aumgn.bukkitutils.command.exception.NoSuchMaterial;
import fr.aumgn.bukkitutils.util.MaterialAndData;

public class CommandArgsTest {

    @Test
    public void testIntegerArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "1");

        assertEquals(1, args.getInteger(1));
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidIntegerArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "arg2");

        args.getInteger(1);
    }

    @Test
    public void testDoubleArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "1.0");

        assertEquals(1.0, args.getDouble(1), 0);
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidDoubleArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "args2");

        args.getDouble(1);
    }

    @Test
    public void testMaterial() {
        CommandArgs args = CommandArgsUtil.parse("stone",
                String.valueOf(Material.STONE.getId()));

        assertEquals(Material.STONE, args.getMaterial(0));
        assertEquals(Material.STONE, args.getMaterial(1));
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterial() {
        CommandArgs args = CommandArgsUtil.parse(
                "nomaterialshouldeverhavethisname");

        args.getMaterial(0);
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterialId() {
        CommandArgs args = CommandArgsUtil.parse(
                String.valueOf(Integer.MAX_VALUE));

        args.getMaterial(0);
    }

    @Test
    public void testMaterialAndData() {
        CommandArgs args = CommandArgsUtil.parse("stone:3");
        MaterialAndData materialAndData = args.getMaterialAndData(0);

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(3, materialAndData.getData());
    }

    @Test
    public void testMaterialAndDataWithoutData() {
        CommandArgs args = CommandArgsUtil.parse("stone");
        MaterialAndData materialAndData = args.getMaterialAndData(0);

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(0, materialAndData.getData());
    }

    @Test(expected = InvalidMaterialAndDataFormat.class)
    public void testInvalidMaterialAndDataFormat() {
        CommandArgs args = CommandArgsUtil.parse("stone:4:5");

        args.getMaterialAndData(0);
    }

    @Test(expected = InvalidMaterialAndDataFormat.class)
    public void testInvalidMaterialAndDataData() {
        CommandArgs args = CommandArgsUtil.parse("stone:invaliddata");

        args.getMaterialAndData(0);
    }
}
