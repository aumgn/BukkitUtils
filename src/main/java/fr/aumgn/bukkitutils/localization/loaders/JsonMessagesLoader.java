package fr.aumgn.bukkitutils.localization.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.util.Map;

public class JsonMessagesLoader extends MessagesLoader {

    @Override
    public String[] getExtensions() {
        return new String[] { "json" };
    }

    @Override
    public Map<?, ?> loadRaw(Reader reader) {
        Gson gson = new GsonBuilder()
                .create();

        TypeToken<Map<String, Object>> typeToken =
                new TypeToken<Map<String, Object>>() {
                };
        Object obj = gson.fromJson(reader, typeToken.getRawType());
        return (Map<?, ?>) obj;
    }
}
