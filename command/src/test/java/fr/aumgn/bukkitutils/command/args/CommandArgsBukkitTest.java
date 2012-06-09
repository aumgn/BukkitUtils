package fr.aumgn.bukkitutils.command.args;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.arg.bukkit.PlayerArg.NoSuchPlayer;
import fr.aumgn.bukkitutils.command.arg.bukkit.WorldArg.NoSuchWorld;
import fr.aumgn.bukkitutils.mockbukkit.MockBukkit;

public class CommandArgsBukkitTest {

    private Player player;
    private World world;

    @Before
    public void setUp() {
        MockBukkit.setUp();
        player = MockBukkit.mockPlayer("Player");
        world = MockBukkit.mockWorld("world");
    }

    @After
    public void tearDown() {
        MockBukkit.tearDown();
    }

    @Test
    public void testGetPlayer() {
        CommandArgs args = CommandArgsUtil.parse("Player", "player");

        assertEquals(player, args.getPlayer(0).value());
        assertEquals(player, args.getPlayer(1).value());
    }

    /*@Test
    public void testMatchAllPlayers() {
        MockBukkit.mockPlayer("Player2");
        MockBukkit.mockPlayer("Player3");
        CommandArgs args = CommandArgsUtil.parse("*");

        assertEquals(3, args.getPlayers(0).match().size());
    }*/

    @Test
    public void testMatchPlayer() {
        CommandArgs args = CommandArgsUtil.parse("Pla", "pl");

        assertEquals(player, args.getPlayer(0).value());
        assertEquals(player, args.getPlayer(1).value());
    }

    @Test
    public void testMatchPlayers() {
        MockBukkit.mockPlayer("Player2");
        MockBukkit.mockPlayer("Player3");
        MockBukkit.mockPlayer("Intruder");
        CommandArgs args = CommandArgsUtil.parse("Pla");
        List<Player> players = args.getPlayers(0).match();
        CommandArgs args2 = CommandArgsUtil.parse("Pla,in");
        List<Player> players2 = args2.getPlayers(0).match();

        assertEquals(3, players.size());
        assertEquals(4, players2.size());
    }

    @Test(expected = NoSuchPlayer.class)
    public void testNoSuchPlayer() {
        CommandArgs args = CommandArgsUtil.parse("NotAPlayer");

        args.getPlayer(0).value();
    }

    @Test(expected = NoSuchPlayer.class)
    public void testNoSuchPlayerWithGetPlayers() {
        CommandArgs args = CommandArgsUtil.parse("NotAPlayer");

        args.getPlayers(0).value();
    }

    @Test
    public void testGetWorld() {
        CommandArgs args = CommandArgsUtil.parse("world");

        assertEquals(world, args.getWorld(0).value());
    }

    @Test(expected = NoSuchWorld.class)
    public void testNoSuchWorld() {
        CommandArgs args = CommandArgsUtil.parse("NotAWorld");

        args.getWorld(0).value();
    }
}
