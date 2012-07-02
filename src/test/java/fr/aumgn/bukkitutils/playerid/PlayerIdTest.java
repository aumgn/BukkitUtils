package fr.aumgn.bukkitutils.playerid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.aumgn.bukkitutils.mockbukkit.MockBukkit;

import static org.junit.Assert.*;

public class PlayerIdTest {

    @Before
    public void setUp() {
        MockBukkit.setUp();
        MockBukkit.mockPlayer("Player");
    }

    @After
    public void tearDown() {
        MockBukkit.tearDown();
    }

    @Test
    public void testPlayerIdCache() {
        PlayerId playerId1 = PlayerId.get("Player");
        PlayerId playerId2 = PlayerId.get("Player");

        assertSame(playerId1, playerId2);
    }

    @Test
    public void testPlayerIdCaseInsensitivity() {
        PlayerId playerId1 = PlayerId.get("Player");
        PlayerId playerId2 = PlayerId.get("player");

        assertEquals(playerId1, playerId2);
    }

    @Test
    public void testIsOnline() {
        PlayerId playerId1 = PlayerId.get("Player");
        PlayerId playerId2 = PlayerId.get("OfflinePlayer");

        assertTrue(playerId1.isOnline());
        assertFalse(playerId1.isOffline());
        
        assertFalse(playerId2.isOnline());
        assertTrue(playerId2.isOffline());
    }
}
