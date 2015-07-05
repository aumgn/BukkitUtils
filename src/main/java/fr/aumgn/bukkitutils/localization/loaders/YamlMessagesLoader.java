package fr.aumgn.bukkitutils.localization.loaders;

import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.Collections;
import java.util.Map;

public class YamlMessagesLoader extends MessagesLoader {

    @Override
    public String[] getExtensions() {
        return new String[] { "yml", "yaml" };
    }

    @Override
    public Map<?, ?> loadRaw(Reader reader) {
        Yaml yaml = new Yaml();
        Object obj = yaml.load(reader);
        if (!(obj instanceof Map)) {
            return Collections.<String, String>emptyMap();
        }

        return (Map<?, ?>) obj;
    }
}
