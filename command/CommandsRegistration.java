package fr.aumgn.bukkitutils.command;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aumgn.bukkitutils.command.executor.MethodCommandExecutor;
import fr.aumgn.bukkitutils.command.executor.NestedCommandExecutor;

public class CommandsRegistration {

    private JavaPlugin plugin;

    public CommandsRegistration(JavaPlugin plugin) {
        this.plugin = plugin; 
    }

    public void register(Commands commands) {
        NestedCommands subCmdsAnnotation =
                commands.getClass().getAnnotation(NestedCommands.class);
        boolean subCommands;
        if (subCmdsAnnotation != null) {
            subCommands = true;
        } else {
            subCommands = false;
        }

        for (Method method : commands.getClass().getMethods()) {
            Command cmdAnnotation = method.getAnnotation(Command.class);
            if (cmdAnnotation == null || !isValidCommand(method)) {
                continue;
            }

            String name;
            if (subCommands) {
                name = subCmdsAnnotation.name() + " " + cmdAnnotation.name();
                System.out.println(name);
                registerSubCommand(subCmdsAnnotation.name(), cmdAnnotation.name());
            } else {
                name = cmdAnnotation.name();
            }
            PluginCommand command = plugin.getCommand(name);
            if (command != null) {
                setCommandMessages(command);
                CommandExecutor oldExecutor = command.getExecutor();
                CommandExecutor executor = new MethodCommandExecutor(
                        commands, method, cmdAnnotation);
                if (oldExecutor instanceof NestedCommandExecutor) {
                    ((NestedCommandExecutor) oldExecutor).setDefaultExecutor(executor);
                } else {
                    command.setExecutor(executor);
                }
            }
        }
    }

    private void setCommandMessages(PluginCommand command) {
        command.setUsage(ChatColor.GREEN + "Utilisation : " +
                ChatColor.YELLOW + command.getUsage());
        command.setPermissionMessage(ChatColor.RED + 
                "Vous n'avez pas la permission d'exécuter cette commande.");
    }

    private boolean isValidCommand(Method method) {
        Class<?>[] params = method.getParameterTypes();
        if (params.length < 2) {
            return false;
        }
        if (!CommandSender.class.isAssignableFrom(params[0])) {
            return false;
        }
        if (!CommandArgs.class.isAssignableFrom(params[1])) {
            return false;
        }

        return true;
    }

    private void registerSubCommand(String name, String subCmdName) {
        PluginCommand command = plugin.getCommand(name);
        CommandExecutor executor = command.getExecutor();
        if (executor instanceof NestedCommandExecutor) {
            ((NestedCommandExecutor) executor).addSubCommand(subCmdName);
        } else {
            NestedCommandExecutor nestedExecutor = new NestedCommandExecutor(plugin, name);
            nestedExecutor.setDefaultExecutor(executor);
            nestedExecutor.addSubCommand(subCmdName);
            setCommandMessages(command);
            command.setExecutor(nestedExecutor);
        }
    }
}
