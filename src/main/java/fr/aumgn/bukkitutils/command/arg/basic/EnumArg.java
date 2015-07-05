package fr.aumgn.bukkitutils.command.arg.basic;

import com.google.common.base.Function;
import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.impl.AbstractCommandArg;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.glob.Glob;

import java.util.List;
import java.util.Locale;

public class EnumArg<T extends Enum<T>> extends AbstractCommandArg<T> {

    private final CommandsMessages messages;
    private final Class<T> enumClass;

    public EnumArg(CommandsMessages messages, String string,
                   Class<T> enumClass) {
        super(string);
        this.messages = messages;
        this.enumClass = enumClass;
    }

    private T[] values() {
        return enumClass.getEnumConstants();
    }

    @Override
    public T value() {
        List<T> matched = match();

        if (matched.size() > 1) {
            throw new MoreThanOneValidValueFound(messages, string);
        }

        return matched.get(0);
    }

    public List<T> match() {
        List<T> matched = new Glob(string.toLowerCase(Locale.ENGLISH))
                .fromStart().build(new Function<T, String>() {
                    @Override
                    public String apply(T value) {
                        return value.name().toLowerCase(Locale.ENGLISH);
                    }
                }).filter(values());

        if (matched.isEmpty()) {
            throw new EnumArgNotFound(messages, values());
        }

        return matched;
    }

    public static class EnumArgNotFound extends CommandError {

        private static final long serialVersionUID = -3141379222300157186L;

        public EnumArgNotFound(CommandsMessages messages,
                               Enum<? extends Enum<?>>[] values) {
            super(messages.get("enumarg-notfound", formatValues(values)));
        }

        private static String formatValues(Enum<?>[] values) {
            if (values.length == 0) {
                return "";
            }

            StringBuilder builder = new StringBuilder();
            builder.append(values[0].name().toLowerCase(Locale.ENGLISH));
            for (int i = 1; i < values.length; i++) {
                builder.append(", ");
                builder.append(values[i].name().toLowerCase(Locale.ENGLISH));
            }

            return builder.toString();
        }
    }

    public static class MoreThanOneValidValueFound extends CommandError {

        private static final long serialVersionUID = -4412257907548635863L;

        public MoreThanOneValidValueFound(CommandsMessages messages, String name) {
            super(messages.get("enumarg-morethanonefound", name));
        }
    }
}
