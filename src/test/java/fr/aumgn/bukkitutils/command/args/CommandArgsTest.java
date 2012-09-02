package fr.aumgn.bukkitutils.command.args;

import org.bukkit.Material;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.arg.basic.EnumArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.ItemTypeArg.InvalidItemTypeFormat;
import fr.aumgn.bukkitutils.command.arg.bukkit.MaterialArg.NoSuchMaterial;
import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;
import fr.aumgn.bukkitutils.itemtype.ItemType;

public class CommandArgsTest {

    @Test
    public void testIntegerArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "1");

        assertEquals(1, (int) args.getInteger(1).value());
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidIntegerArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "arg2");

        args.getInteger(1).value();
    }

    @Test
    public void testDefaultIntegerArg() {
        CommandArgs args = CommandArgsUtil.parse("10");

        assertEquals(10, (int) args.getInteger(0).valueOr(1));
        assertEquals(1, (int) args.getInteger(1).valueOr(1));
    }
    @Test
    public void testDoubleArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "1.0");

        assertEquals(1.0, args.getDouble(1).value(), 0);
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidDoubleArgs() {
        CommandArgs args = CommandArgsUtil.parse("arg1", "args2");

        args.getDouble(1).value();
    }

    @Test
    public void testGetVector() {
        CommandArgs args1 = CommandArgsUtil.parse("1.0");
        CommandArgs args2 = CommandArgsUtil.parse("1,2.0");
        CommandArgs args3 = CommandArgsUtil.parse("1,2,3.3");

        assertEquals(new Vector(1, 0, 0), args1.getVector(0).value());
        assertEquals(new Vector(1, 2, 0), args2.getVector(0).value());
        assertEquals(new Vector(1, 2, 3.3), args3.getVector(0).value());
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidVectorComponent() {
        CommandArgs args = CommandArgsUtil.parse("1,invalid,4");

        args.getVector(0).value();
    }

    @Test
    public void testGetVector2D() {
        CommandArgs args1 = CommandArgsUtil.parse("1");
        CommandArgs args2 = CommandArgsUtil.parse("1,2.5");

        assertEquals(new Vector2D(1, 0), args1.getVector2D(0).value());
        assertEquals(new Vector2D(1, 2.5), args2.getVector2D(0).value());
    }

    @Test(expected = CommandUsageError.class)
    public void testInvalidVector2DComponent() {
        CommandArgs args = CommandArgsUtil.parse("1,invalid");

        args.getVector2D(0).value();
    }

    public enum TestArg {
        Value1,
        Value2
    }

    @Test
    public void testEnum() {
        CommandArgs args = CommandArgsUtil.parse("value1", "v*2", "v*");

        assertSame(TestArg.Value1, args.get(0, TestArg.class).value());
        assertSame(TestArg.Value2, args.get(1, TestArg.class).value());
        assertEquals(2, args.get(2, TestArg.class).match().size());
    }

    @Test(expected = EnumArg.EnumArgNotFound.class)
    public void testNotFound() {
        CommandArgs args = CommandArgsUtil.parse("a");

        args.get(0, TestArg.class).value();
    }

    @Test(expected = EnumArg.MoreThanOneValidValueFound.class)
    public void testMoreThanOneValidValueFound() {
        CommandArgs args = CommandArgsUtil.parse("v");

        args.get(0, TestArg.class).value();
    }

    @Test
    public void testMaterial() {
        CommandArgs args = CommandArgsUtil.parse("stone",
                String.valueOf(Material.STONE.getId()));

        assertEquals(Material.STONE, args.getMaterial(0).value());
        assertEquals(Material.STONE, args.getMaterial(1).value());
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterial() {
        CommandArgs args = CommandArgsUtil.parse(
                "nomaterialshouldeverhavethisname");

        args.getMaterial(0).value();
    }

    @Test(expected = NoSuchMaterial.class)
    public void testInvalidMaterialId() {
        CommandArgs args = CommandArgsUtil.parse(
                String.valueOf(Integer.MAX_VALUE));

        args.getMaterial(0).value();
    }

    @Test
    public void testItemType() {
        CommandArgs args = CommandArgsUtil.parse("stone:3");
        ItemType materialAndData = args.getItemType(0).value();

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(3, materialAndData.getData());
    }

    @Test
    public void testItemTypeWithSpecificData() {
        CommandArgs args = CommandArgsUtil.parse("wool:orange", "wood:jungle");
        ItemType materialAndData = args.getItemType(0).value();
        ItemType materialAndData2 = args.getItemType(1).value();

        assertEquals(Material.WOOL, materialAndData.getMaterial());
        assertEquals(1, materialAndData.getData());

        assertEquals(Material.WOOD, materialAndData2.getMaterial());
        assertEquals(3, materialAndData2.getData());
    }

    @Test
    public void testItemTypeWithoutData() {
        CommandArgs args = CommandArgsUtil.parse("stone");
        ItemType materialAndData = args.getItemType(0).value();

        assertEquals(Material.STONE, materialAndData.getMaterial());
        assertEquals(0, materialAndData.getData());
    }

    @Test(expected = InvalidItemTypeFormat.class)
    public void testInvalidItemTypeFormat() {
        CommandArgs args = CommandArgsUtil.parse("stone:4:5");

        args.getItemType(0).value();
    }

    @Test(expected = InvalidItemTypeFormat.class)
    public void testInvalidItemTypeData() {
        CommandArgs args = CommandArgsUtil.parse("stone:invaliddata");

        args.getItemType(0).value();
    }
}
