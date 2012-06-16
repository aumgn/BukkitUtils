package fr.aumgn.bukkitutils.command;

import java.lang.reflect.Method;

import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aumgn.bukkitutils.command.args.CommandArgs;
import fr.aumgn.bukkitutils.command.executor.MethodCommandExecutor;
import fr.aumgn.bukkitutils.command.executor.NestedCommandExecutor;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandsRegistration {

    private JavaPlugin plugin;
    private Messages local;

    public CommandsRegistration(JavaPlugin plugin, Messages local) {
        this.plugin = plugin;
        this.local = local;
    }

    public void register(Commands commands) {
        NestedCommands subCmdsAnnotation =
                commands.getClass().getAnnotation(NestedCommands.class);
        boolean subCommands = false;
        if (subCmdsAnnotation != null) {
            subCommands = true;
        }

        Method preExecute = null;
        try {
            preExecute = commands.getClass().getMethod("preExecute", CommandSender.class, CommandArgs.class);
            Class<?>[] params = preExecute.getParameterTypes();
            Validate.isTrue(params.length == 2,
                    "preExecute method must define two arguments.");
            Validate.isTrue(CommandSender.class.isAssignableFrom(params[0]),
                    "First argument must be of type CommandSender.");
            Validate.isTrue(CommandSender.class.isAssignableFrom(params[1]),
                    "Second argument must be of type CommandArgs.");
        } catch (Exception e) {
        }

        for (Method method : commands.getClass().getMethods()) {
            Command cmdAnnotation = method.getAnnotation(Command.class);
            if (cmdAnnotation == null) {
                continue;
            }
            validateCommand(method);
            Validate.notEmpty(cmdAnnotation.name());

            String cmdName;
            if (subCommands) {
                String[] nestedCmds = subCmdsAnnotation.name();
                Validate.notEmpty(nestedCmds);
                Validate.notEmpty(nestedCmds[0]);

                StringBuilder cmd = new StringBuilder();
                cmd.append(nestedCmds[0]);
                for (int i = 1; i < nestedCmds.length; i++) {
                    registerSubCommand(cmd.toString(), nestedCmds[i]);
                    cmd.append(" ");
                    cmd.append(nestedCmds[i]);
                }
                registerSubCommand(cmd.toString(), cmdAnnotation.name());
                cmdName = cmd + " " + cmdAnnotation.name();
            } else {
                cmdName = cmdAnnotation.name();
            }
            PluginCommand command = plugin.getCommand(cmdName);
            Validate.notNull(command,
                    String.format("Command '%s' does not exist", cmdName));

            if (command != null) {
                CommandExecutor oldExecutor = command.getExecutor();
                CommandExecutor executor = new MethodCommandExecutor(
                        local, commands, preExecute, method, cmdAnnotation);
                if (oldExecutor instanceof NestedCommandExecutor) {
                    ((NestedCommandExecutor) oldExecutor).setDefaultExecutor(executor);
                } else {
                    setCommandMessages(command);
                    command.setExecutor(executor);
                }
            }
        }
    }

    private void setCommandMessages(PluginCommand command) {
        command.setUsage(local.usagePrefix() + command.getUsage());
        command.setPermissionMessage(local.permissionMessage());
    }

    private void validateCommand(Method method) {
        Class<?>[] params = method.getParameterTypes();
        Validate.isTrue(params.length == 1 || params.length == 2,
                "Command method must define one or two parameter(s).");
        Validate.isTrue(CommandSender.class.isAssignableFrom(params[0]),
                "First parameter of command method must be of type CommandSender");
        Validate.isTrue(params.length == 1
                || CommandArgs.class.isAssignableFrom(params[1]),
                "Second parameter of command method must be of type CommandArgs");
    }

    private void registerSubCommand(String name, String subCmdName) {
        PluginCommand command = plugin.getCommand(name);
        Validate.notNull(command, String.format("Command '%s' does not exist", name));
        CommandExecutor executor = command.getExecutor();
        if (executor instanceof NestedCommandExecutor) {
            ((NestedCommandExecutor) executor).addSubCommand(subCmdName);
        } else {
            NestedCommandExecutor nestedExecutor = new NestedCommandExecutor(plugin, name);
            nestedExecutor.setDefaultExecutor(executor);
            nestedExecutor.addSubCommand(subCmdName);
            if (!(executor instanceof MethodCommandExecutor)) {
                setCommandMessages(command);
            }
            command.setExecutor(nestedExecutor);
        }
    }
}
