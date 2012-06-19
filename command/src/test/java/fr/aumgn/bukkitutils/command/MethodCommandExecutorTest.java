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

    private CommandExecutor getExecutorFor(Commands commands) {
        Method preExecute = null;
        Method method = null;
        for (Method declaredMethod : commands.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("preExecute")) {
                preExecute = declaredMethod;
                continue;
            }
            if (declaredMethod.getAnnotation(Command.class) != null) {
                method = declaredMethod;
            }
        }

        Command command = method.getAnnotation(Command.class);
        return new MethodCommandExecutor(new Messages(null),
                commands, preExecute, method, command);
    }

    @Test
    public void testExecution() {
        CommandExecutor executor = getExecutorFor(new TestCommands());
        executor.onCommand(player, null, "test", new String[] { "Hi !" });

        verify(player).sendMessage("Hi !");
    }

    @Test
    public void testPreExecute() {
        CommandExecutor executor = getExecutorFor(new TestPreExecuteCommands());
        executor.onCommand(player, null, "test", new String[] { "Hi !" });

        verify(player).sendMessage("Hi !");
    }

    @Test
    public void testCommandWithoutArg() {
        CommandExecutor executor = getExecutorFor(new TestCommandsWithoutArg());
        executor.onCommand(player, null, "test", new String[] {});

        verify(player).sendMessage("Hi !");
    }
}
