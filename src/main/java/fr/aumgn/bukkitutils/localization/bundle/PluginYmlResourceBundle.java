package fr.aumgn.bukkitutils.localization.bundle;

import java.io.Reader;
import java.util.Collections;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class PluginYmlResourceBundle extends PluginResourceBundle {

    @SuppressWarnings("unchecked")
    private static Map<String, Object> readYml(Reader reader) {
        Yaml yaml = new Yaml();
        Object obj = yaml.load(reader);
        if (!(obj instanceof Map)) {
            return Collections.<String, Object>emptyMap();
        }

        return (Map<String, Object>) obj;
    }

    public PluginYmlResourceBundle(Reader reader) {
        super(readYml(reader));
    }
}
