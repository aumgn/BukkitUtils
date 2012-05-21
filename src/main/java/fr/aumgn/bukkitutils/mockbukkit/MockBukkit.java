package fr.aumgn.bukkitutils.mockbukkit;

import static org.mockito.Mockito.*;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public final class MockBukkit {

    private static MockServer server;
    private static boolean initialized = false;

    public static void setUp() {
        if (!initialized) {
            server = new MockServer();
            Bukkit.setServer(server);
            initialized = true;
            server.init();
        }
    }

    public static void tearDown() {
        server.getPlayersMap().clear();
        server.getWorldsMap().clear();
    }

    public static Player mockPlayer(String name) {
        Player player = mock(Player.class);
        server.getPlayersMap().put(name.toLowerCase(), player);
        when(player.getName()).thenReturn(name);
        when(player.getDisplayName()).thenReturn(name);
        when(player.getPlayerListName()).thenReturn(name);
        return player;
    }

    public static World mockWorld(String name) {
        World world = mock(World.class);
        server.getWorldsMap().put(name.toLowerCase(), world);
        when(world.getName()).thenReturn(name);
        return world;
    }

    private MockBukkit() {
    }
}
