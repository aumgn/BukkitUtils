package fr.aumgn.bukkitutils.command;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.Command;
import fr.aumgn.bukkitutils.command.Commands;

public class TestCommandsWithoutArg implements Commands {

    @Command(name = "test")
    public void test(CommandSender sender) {
        sender.sendMessage("Hi !");
    }
}
