package fr.aumgn.bukkitutils.localization.loaders;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class PropertiesMessagesLoader extends MessagesLoader {

    @Override
    public String[] getExtensions() {
        return new String[] { "properties" };
    }

    @Override
    public Map<?, ?> loadRaw(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
            return properties;
        }
        catch (IOException exc) {
            return Collections.<String, String>emptyMap();
        }
    }
}
