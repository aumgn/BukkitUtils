package fr.aumgn.bukkitutils.command;

import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.executor.MethodCommandExecutor;
import fr.aumgn.bukkitutils.command.executor.NestedCommandExecutor;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Class which handles all the mess of registering commands and nested commands
 * using the Bukkit API.
 */
public class CommandsRegistration {

    private static final String PREEXECUTE_METHOD_NAME = "preExecute";

    private final JavaPlugin plugin;
    private final CommandsMessages messages;

    public CommandsRegistration(JavaPlugin plugin, Locale locale) {
        this.plugin = plugin;
        CommandsLocalization localisation =
                new CommandsLocalization(plugin, locale);
        this.messages = localisation.get("commands");
    }

    /**
     * Registers all defined command in the given {@link Commands} object.
     */
    public void register(Commands commands) {
        Method preExecute = getPreExecuteMethod(commands);

        String cmdPrefix = "";
        if (commands.getClass().isAnnotationPresent(NestedCommands.class)) {
            cmdPrefix = registerNestedCommand(commands, preExecute);
        }


        for (Method method : commands.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Command.class)) {
                continue;
            }

            Command cmdAnnotation = method.getAnnotation(Command.class);
            validateCommand(method, false);
            Validate.notEmpty(cmdAnnotation.name());

            String cmdName = cmdPrefix + cmdAnnotation.name();
            PluginCommand command = plugin.getCommand(cmdName);
            Validate.notNull(command, String.format("Command '%s' does not exist", cmdName));
            CommandExecutor executor = new MethodCommandExecutor(messages, commands, preExecute, method, cmdAnnotation);
            setCommand(command, executor);
        }
    }

    private Method getPreExecuteMethod(Commands commands) {
        try {
            Method preExecute = commands.getClass().getMethod(
                    PREEXECUTE_METHOD_NAME, CommandSender.class,
                    CommandArgs.class);
            validateCommand(preExecute, true);
            return preExecute;
        }
        catch (NoSuchMethodException exc) {
            return null;
        }
    }

    private String registerNestedCommand(Commands commands, Method preExecute) {
        NestedCommands annotation =
                commands.getClass().getAnnotation(NestedCommands.class);
        String[] nestedCmds = annotation.value();
        Validate.notEmpty(nestedCmds);
        Validate.notEmpty(nestedCmds[0]);

        StringBuilder fullName = new StringBuilder();

        for (String name : nestedCmds) {
            Validate.notEmpty(name);
            fullName.append(name);

            PluginCommand command = plugin.getCommand(fullName.toString());
            Validate.notNull(command, String.format("Command '%s' does not exist", name));
            CommandExecutor executor = command.getExecutor();
            if (!(executor instanceof NestedCommandExecutor)) {
                setCommand(command, new NestedCommandExecutor(plugin, messages, annotation.defaultTo()));
            }
            fullName.append(" ");
        }

        return fullName.toString();
    }

    private void setCommand(PluginCommand command, CommandExecutor executor) {
        CommandExecutor oldExecutor = command.getExecutor();
        command.setExecutor(executor);
        if (oldExecutor instanceof MethodCommandExecutor) {
            return;
        }

        command.setUsage(messages.usageMessage(command.getUsage()));
        command.setPermissionMessage(messages.permissionMessage());
    }

    private void validateCommand(Method method, boolean strictLength) {
        Class<?>[] params = method.getParameterTypes();
        if (strictLength) {
            Validate.isTrue(params.length == 2,
                    "Command method must define two parameters.");
        }
        else {
            Validate.isTrue(params.length == 1 || params.length == 2,
                    "Command method must define one or two parameter(s).");
        }
        Validate.isTrue(CommandSender.class.isAssignableFrom(params[0]),
                "First parameter of command method must "
                        + "be of type CommandSender");
        Validate.isTrue(params.length == 1
                        || CommandArgs.class.isAssignableFrom(params[1]),
                "Second parameter of command method must "
                        + "be of type CommandArgs");
    }
}
