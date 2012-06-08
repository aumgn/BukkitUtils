package fr.aumgn.bukkitutils.command.arg.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class ListArg<V> extends CommandArg<List<V>> {

    public static class Factory<V> extends CommandArgFactory<List<V>> {

        private final CommandArgFactory<V> factory;

        public Factory(CommandArgFactory<V> factory) {
            this.factory = factory;
        }

        @Override
        public CommandArg<List<V>> createCommandArg(Messages messages, String string) {
            return new ListArg<V>(factory, messages, string);
        }
    }

    private final CommandArgFactory<V> factory;

    public ListArg(CommandArgFactory<V> factory, Messages messages, String string) {
        super(messages, string);
        this.factory = factory;
    }

    @Override
    public List<V> value() {
        String[] splitted = string.split(",");
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            list.addAll(factory
                    .createCommandArg(messages, subArg)
                    .match());
        }

        return list;
    }

    @Override
    public List<V> value(List<V> def) {
        return super.value(def);
    }

    public List<V> value(V def) {
        if (string == null) {
            return Collections.<V>singletonList(def);
        }

        return value();
    }

    @Override
    public List<V> value(CommandSender sender) {
        if (string == null) {
            return Collections.<V>singletonList(
                    factory.createCommandArg(messages, null).value(sender));
        }

        return value();
    }
}
