package fr.aumgn.bukkitutils.localization.bundle;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class PluginPropertyResourceBundle extends PluginResourceBundle {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Map<String, Object> readProperties(Reader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        return new HashMap(properties);
    }

    public PluginPropertyResourceBundle(Reader reader, Locale locale) throws IOException {
        super(readProperties(reader), locale);
    }
}
