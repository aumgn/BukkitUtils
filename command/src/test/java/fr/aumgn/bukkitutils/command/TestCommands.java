package fr.aumgn.bukkitutils.command;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.args.CommandArgs;

public class TestCommands implements Commands {

    @Command(name = "test", min = 1, max = 1)
    public void test(CommandSender sender, CommandArgs args) {
        sender.sendMessage(args.get(0));
    }
}
