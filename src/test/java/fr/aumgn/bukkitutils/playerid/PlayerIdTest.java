package fr.aumgn.bukkitutils.playerid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.aumgn.bukkitutils.playerref.PlayerRef;
import fr.aumgn.mockbukkit.MockBukkit;

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
        PlayerRef playerId1 = PlayerRef.get("Player");
        PlayerRef playerId2 = PlayerRef.get("Player");

        assertSame(playerId1, playerId2);
    }

    @Test
    public void testPlayerIdCaseInsensitivity() {
        PlayerRef playerId1 = PlayerRef.get("Player");
        PlayerRef playerId2 = PlayerRef.get("player");

        assertEquals(playerId1, playerId2);
    }

    @Test
    public void testIsOnline() {
        PlayerRef playerId1 = PlayerRef.get("Player");
        PlayerRef playerId2 = PlayerRef.get("OfflinePlayer");

        assertTrue(playerId1.isOnline());
        assertFalse(playerId1.isOffline());
        
        assertFalse(playerId2.isOnline());
        assertTrue(playerId2.isOffline());
    }
}
