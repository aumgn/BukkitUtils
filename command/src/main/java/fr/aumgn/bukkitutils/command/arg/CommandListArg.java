package fr.aumgn.bukkitutils.command.arg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.messages.Messages;

public class CommandListArg<V> extends AbstractCommandArg<List<V>, V> {

    private final CommandArgFactory<V> factory;

    public CommandListArg(CommandArgFactory<V> factory, Messages messages, String string) {
        super(messages, string);
        this.factory = factory;
    }

    @Override
    public List<V> value() {
        String[] splitted = string.split(",");
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            list.add(factory
                    .createCommandArg(messages, subArg)
                    .value());
        }

        return list;
    }

    @Override
    public List<V> value(List<V> def) {
        return super.value(def);
    }

    public List<V> valueSingleton(V def) {
        return value(Collections.<V>singletonList(def));
    }

    @Override
    public List<V> value(CommandSender sender) {
        if (string == null) {
            V value = factory
                    .createCommandArg(messages, null)
                    .value(sender);
            return Collections.<V>singletonList(value);
        }

        return value();
    }

    @Override
    public List<V> match() {
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
    public List<V> match(List<V> def) {
        return super.match(def);
    }

    @Override
    public List<V> match(V def) {
        return match(Collections.<V>singletonList(def));
    }

    @Override
    public List<V> match(CommandSender sender) {
        if (string == null) {
            return factory
                    .createCommandArg(messages, null)
                    .match(sender);
        }

        return match();
    }
}
