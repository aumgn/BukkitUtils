package fr.aumgn.bukkitutils.localization;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;

public class PluginMessages {

    private Map<String, MessageFormat> map;

    public PluginMessages(Map<String, MessageFormat> map) {
        Validate.notNull(map);
        this.map = map;
    }

    public boolean has(String key) {
        Validate.notNull(key);
        return map.containsKey(key);
    }

    public String get(String key) {
        return get(key, new Object[0]);
    }

    public String get(String key, Object... arguments) {
        if (!map.containsKey(key)) {
            return ChatColor.RED + "## Missing message for key \""
                    + key + "\" ##";
        }

        MessageFormat message = map.get(key);
        return message.format(arguments);
    }

    public Set<String> keys() {
        return Collections.unmodifiableSet(map.keySet());
    }

    public Set<Entry<String, MessageFormat>> entries() {
        return Collections.unmodifiableSet(map.entrySet());
    }
}
