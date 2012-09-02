package fr.aumgn.bukkitutils.command.arg.basic;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.MatchingArg;
import fr.aumgn.bukkitutils.command.arg.SenderArg;
import fr.aumgn.bukkitutils.command.arg.SenderMatchingArg;

public class ListArg<V, T extends CommandArg<V>> implements CommandArg<List<V>>,
        MatchingArg<V>, SenderArg<List<V>>, SenderMatchingArg<V>, Iterable<T> {

    private final String string;
    private final String separator;
    private final CommandArgFactory<T> factory;

    public ListArg(String separator,
            CommandArgFactory<T> factory, String string) {
        this.string = string;
        this.separator = separator;
        this.factory = factory;
    }

    @Override
    public List<V> value() {
        String[] splitted = string.split(separator);
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            list.add(factory
                    .createCommandArg(subArg)
                    .value());
        }

        return list;
    }

    public List<V> valueOrSingleton(V def) {
        return valueOr(singletonList(def));
    }

    @Override
    public List<V> valueOr(List<V> def) {
        if (string != null) {
            return value();
        }

        return def;
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link SenderArg} or
     * this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> valueOr(CommandSender sender) {
        if (string != null) {
            return match();
        }

        T arg = factory.createCommandArg(null);
        if (!(arg instanceof SenderArg)) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        SenderArg<V> senderArg = (SenderArg<V>) arg;
        return singletonList(senderArg.valueOr(sender));
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link SenderArg} or
     * this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> valueWithPermOr(String permission, CommandSender sender) {
        if (string != null) {
            return match();
        }

        T arg = factory.createCommandArg(null);
        if (!(arg instanceof SenderArg)) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        SenderArg<V> senderArg = (SenderArg<V>) arg;
        return singletonList(
                senderArg.valueWithPermOr(permission, sender));
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link MatchingArg} or
     * this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<V> match() {
        String[] splitted = string.split(separator);
        List<V> list = new ArrayList<V>();

        for (String subArg : splitted) {
            T arg = factory.createCommandArg(subArg);
            if (!(arg instanceof MatchingArg)) {
                throw new UnsupportedOperationException();
            }

            list.addAll(((MatchingArg<V>) arg).match());
        }

        return list;
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link MatchingArg} or
     * this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> matchOr(V def) {
        return matchOr(singletonList(def));
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link MatchingArg} or
     * this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> matchOr(List<V> def) {
        if (string != null) {
            return match();
        }

        return def;
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link SenderMatchingArg}
     * or this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> matchOr(CommandSender sender) {
        if (string != null) {
            return match();
        }

        T arg = factory.createCommandArg(null);
        if (!(arg instanceof SenderMatchingArg)) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        SenderMatchingArg<V> senderArg = (SenderMatchingArg<V>) arg;
        return senderArg.matchOr(sender);
    }

    /**
     * {@inheritDoc}
     * Note that the {@link CommandArg} type argument of
     * this ListArg must implement {@link SenderMatchingArg}
     * or this will throws an UnsupportedOperationException.
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public List<V> matchWithPermOr(String permission, CommandSender sender) {
        if (string != null) {
            return match();
        }

        T arg = factory.createCommandArg(null);
        if (!(arg instanceof SenderMatchingArg)) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        SenderMatchingArg<V> senderArg = (SenderMatchingArg<V>) arg;
        return senderArg.matchWithPermOr(permission,
                sender);
    }

    @Override
    public Iterator<T> iterator() {
        if (string == null) {
            return Iterators.singletonIterator(factory.createCommandArg(null));
        } else {
            final String[] splitted = string.split(separator);
            return new AbstractIterator<T>() {
                private int i = 0;
                @Override
                protected T computeNext() {
                    if (i == splitted.length) {
                        endOfData();
                    }
                    return factory.createCommandArg(splitted[++i]);
                }
            };
        }
    }
}
