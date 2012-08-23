package fr.aumgn.bukkitutils.gson.typeadapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@SuppressWarnings("unchecked")
public class BukkitConfigTypeAdapterFactory implements TypeAdapterFactory {

    public static class BukkitConfigTypeAdapter<T> extends TypeAdapter<T> {

        private static final String SERIALIZED_TYPE_KEY = "$$";
        private static final Type BUKKIT_SERIAL_TYPE =
                new TypeToken<HashMap<String, String>>() {}.getType();

        private final Gson gson;

        public BukkitConfigTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public T read(JsonReader reader) throws IOException {
            Map<String, Object> map = gson.fromJson(
                    reader, BUKKIT_SERIAL_TYPE);
            map.put(ConfigurationSerialization.SERIALIZED_TYPE_KEY,
                    map.remove(SERIALIZED_TYPE_KEY));

            return (T) ConfigurationSerialization.deserializeObject(map);
        }

        @Override
        public void write(JsonWriter writer, T obj) throws IOException {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            ConfigurationSerializable serializable =
                    (ConfigurationSerializable) obj;
            map.put(SERIALIZED_TYPE_KEY,
                    ConfigurationSerialization.getAlias(
                            serializable.getClass()));
            map.putAll(serializable.serialize());

            gson.toJson(map, BUKKIT_SERIAL_TYPE, writer);
        }
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> token) {
        if (!ConfigurationSerializable.class.isAssignableFrom(
                token.getRawType())) {
            return null;
        }

        return new BukkitConfigTypeAdapter<T>(gson);
    }
}
