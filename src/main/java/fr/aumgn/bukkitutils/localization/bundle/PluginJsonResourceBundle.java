package fr.aumgn.bukkitutils.localization.bundle;

import java.io.Reader;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PluginJsonResourceBundle extends PluginResourceBundle {

    @SuppressWarnings("unchecked")
    private static Map<String, Object> readJson(Reader reader) {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
        return (Map<String, Object>) gson.fromJson(reader, typeToken.getRawType());
    }

    public PluginJsonResourceBundle(Reader reader, Locale locale) {
        super(readJson(reader), locale);
    }
}
