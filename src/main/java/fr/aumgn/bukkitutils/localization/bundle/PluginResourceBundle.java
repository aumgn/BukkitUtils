package fr.aumgn.bukkitutils.localization.bundle;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.Validate;

import fr.aumgn.bukkitutils.util.Util;

public class PluginResourceBundle extends ResourceBundle {

    private Map<String,Object> map;

    public PluginResourceBundle(Map<String, Object> map, Locale locale) {
        Validate.notNull(locale);
        Validate.notNull(map);
        this.map = map;

        // Parse colors markup
        for (Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof String) {
                String message = Util.parseColorsMarkup((String) obj);
                message = message.replaceAll("'", "''");
                message = message.replaceAll("\\\\", "'");
                entry.setValue(new MessageFormat(message, locale));
            }
        }
    }

    @Override
    public Object handleGetObject(String key) {
        Validate.notNull(key);
        return map.get(key);
    }

    public String get(String key) {
        return get(key, new Object[0]);
    }

    public String get(String key, Object... arguments) {
        Object obj = getObject(key);
        if (obj instanceof MessageFormat) {
            MessageFormat message = (MessageFormat) obj;
            return message.format(arguments);
        } else {
            return (String) MessageFormat.format((String) obj, arguments);
        }
    }

    @Override
    public Enumeration<String> getKeys() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Set<String> handleKeySet() {
        return map.keySet();
    }
}
