package fr.aumgn.bukkitutils.gson.typeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import fr.aumgn.bukkitutils.gson.GsonLoadRuntimeException;

import java.io.IOException;
import java.util.Locale;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> token) {
        if (!token.getRawType().isEnum()) {
            return null;
        }

        return new EnumTypeAdapter(token.getRawType());
    }

    public static class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

        private final Class<T> enumClass;

        public EnumTypeAdapter(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public T read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                return null;
            }

            String name = reader.nextString().toLowerCase(Locale.US);
            for (T element : enumClass.getEnumConstants()) {
                if (element.name().toLowerCase(Locale.US).equals(name)) {
                    return element;
                }
            }

            throw new GsonLoadRuntimeException("Unknown value : " + name);
        }

        @Override
        public void write(JsonWriter writer, T value) throws IOException {
            if (value == null) {
                writer.nullValue();
            }
            else {
                writer.value(value.name());
            }
        }
    }
}
