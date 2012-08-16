package fr.aumgn.bukkitutils.localization.loaders;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import fr.aumgn.bukkitutils.util.Util;

public abstract class MessagesLoader {

    public abstract String[] getExtensions();

    public abstract Map<?, ?> loadRaw(Reader reader);

    public Map<String, MessageFormat> load(
            Locale locale, Reader reader) {
        Map<?, ?> loaded = loadRaw(reader);
        Map<String, MessageFormat> map = new HashMap<String, MessageFormat>();

        for (Entry<?, ?> entry : loaded.entrySet()) {
            String key = entry.getKey().toString();
            String message = entry.getValue().toString();
            map.put(key, parse(locale, message));
        }

        return map;
    }

    protected MessageFormat parse(Locale locale, String rawMessage) {
        String message = Util.parseColorsMarkup(rawMessage);
        message = message.replaceAll("'", "''");
        message = message.replaceAll("\\\\", "'");
        return new MessageFormat(message, locale);
    }
}
