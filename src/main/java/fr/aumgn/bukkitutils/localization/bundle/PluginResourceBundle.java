package fr.aumgn.bukkitutils.localization.bundle;

import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.Validate;

import fr.aumgn.bukkitutils.util.Util;

public class PluginResourceBundle extends ResourceBundle {

    private Map<String,Object> map;

    public PluginResourceBundle(Map<String, Object> map) {
        Validate.notNull(map);
        this.map = map;

        // Parse colors markup
        for (Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof String) {
                entry.setValue(Util.parseColorsMarkup((String) obj));
            }
        }
    }

    @Override
    public Object handleGetObject(String key) {
        Validate.notNull(key);
        return map.get(key);
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
