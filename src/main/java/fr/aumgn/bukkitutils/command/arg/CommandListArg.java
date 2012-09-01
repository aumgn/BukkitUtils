package fr.aumgn.bukkitutils.command.arg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.aumgn.bukkitutils.command.CommandsMessages;

public class CommandListArg<V> extends AbstractCommandArg<List<V>, V> {

    private final String separator;
    private final CommandArgFactory<? extends CommandArg<V>> factory;

    public CommandListArg(CommandsMessages messages,
            CommandArgFactory<? extends CommandArg<V>> factory, String string) {
        this(messages, ",", factory, string);
    }

    public CommandListArg(CommandsMessages messages, String separator,
            CommandArgFactory<? extends CommandArg<V>> factory, String string) {
        super(messages, string);
        this.separator = separator;
        this.factory = factory;
    }

    @Override
    public List<V> value() {
        String[] splitted = string.split(separator);
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            list.add(factory
                    .createCommandArg(messages, subArg)
                    .value());
        }

        return list;
    }

    @Override
    public List<V> valueOr(List<V> def) {
        return super.valueOr(def);
    }

    public List<V> valueOrSingleton(V def) {
        return valueOr(Collections.<V>singletonList(def));
    }

    @Override
    protected List<V> defaultFor(CommandSender sender) {
        V value = factory
                .createCommandArg(messages, null)
                .defaultFor(sender);
        return Collections.<V>singletonList(value);
    }

    @Override
    public List<V> match() {
        String[] splitted = string.split(separator);
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            list.addAll(factory
                    .createCommandArg(messages, subArg)
                    .match());
        }

        return list;
    }

    @Override
    public List<V> matchOr(List<V> def) {
        return super.matchOr(def);
    }

    @Override
    public List<V> matchOr(V def) {
        return matchOr(Collections.<V>singletonList(def));
    }

    @Override
    protected List<V> defaultMatchFor(CommandSender sender) {
        return factory
                .createCommandArg(messages, null)
                .defaultMatchFor(sender);
    }
}
