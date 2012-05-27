package fr.aumgn.bukkitutils.command;

import java.lang.reflect.Method;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import fr.aumgn.bukkitutils.command.executor.MethodCommandExecutor;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.mockbukkit.MockBukkit;

public class MethodCommandExecutorTest {

    private Player player;

    @Before
    public void setUp() {
        MockBukkit.setUp();
        player = MockBukkit.mockPlayer("Player");
    }

    @After
    public void tearDown() {
        MockBukkit.tearDown();
    }

    @Test
    public void testExecution() {
        TestCommands testCommands = new TestCommands();
        Method method = TestCommands.class.getDeclaredMethods()[0];
        Command command = method.getAnnotation(Command.class);
        CommandExecutor executor = new MethodCommandExecutor(new Messages(), testCommands, method, command);
        executor.onCommand(player, null, "test", new String[] { "Hi !" });

        verify(player).sendMessage("Hi !");
    }
}
