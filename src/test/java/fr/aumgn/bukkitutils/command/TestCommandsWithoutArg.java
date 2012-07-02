package fr.aumgn.bukkitutils.command;

import org.bukkit.command.CommandSender;

public class TestCommandsWithoutArg implements Commands {

    @Command(name = "test")
    public void test(CommandSender sender) {
        sender.sendMessage("Hi !");
    }
}
