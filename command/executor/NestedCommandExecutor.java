package fr.aumgn.bukkitutils.command.executor;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class NestedCommandExecutor implements CommandExecutor {

    private JavaPlugin plugin;
    private String name;
    private Set<String> subCommands;
    private CommandExecutor defaultExecutor;

    public NestedCommandExecutor(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.subCommands = new HashSet<String>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            return defaultExecutor.onCommand(sender, cmd, label, args);
        }

        PluginCommand subCommand = plugin.getCommand(name + " " + args[0]);
        if (subCommand == null || !subCommands.contains(subCommand.getName())) {
            return false;
        }

        String[] subCommandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, subCommandArgs, 0, args.length - 1);
        subCommand.execute(sender, label + " " + args[0], subCommandArgs);
        return true;
    }

    public void setDefaultExecutor(CommandExecutor executor) {
        defaultExecutor = executor;
    }

    public void addSubCommand(String subCmdName) {
        subCommands.add(name + " " + subCmdName);
    }
}
