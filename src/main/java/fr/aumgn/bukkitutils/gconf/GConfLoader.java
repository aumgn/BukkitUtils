package fr.aumgn.bukkitutils.gconf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import org.bukkit.plugin.Plugin;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class GConfLoader {

    private final Gson gson;
    private final Plugin plugin;

    public GConfLoader(Gson gson, Plugin plugin) {
        this.gson = gson;
        this.plugin = plugin;
    }

    private File getFile(String filename)
            throws GConfLoadException {
        File folder = plugin.getDataFolder();
        if (!folder.exists() && !folder.mkdirs()) {
            throw new GConfLoadException(
                    "Impossible de créer le dossier :" + folder.getPath());
        }

        return new File(plugin.getDataFolder(), filename);
    }

    public <T> T loadOrCreate(String filename, Class<T> klass)
            throws GConfLoadException {
        return loadOrCreate(filename, klass, klass);
    }

    @SuppressWarnings("unchecked")
    public <T> T loadOrCreate(String filename, TypeToken<T> typeToken)
            throws GConfLoadException {
        return (T) loadOrCreate(filename, typeToken.getType(),
                typeToken.getRawType());
    }

    private <T> T loadOrCreate(String filename, Type type, Class<T> klass)
            throws GConfLoadException {
        try {
            File file = getFile(filename);
            T config;
            if (file.createNewFile()) {
                config = klass.newInstance();
            } else {
                config = load(file, type);
            }

            // This ensures user file is updated with newer fields.
            write(file, config);

            return config;
        } catch (IOException exc) {
            throw new GConfLoadException(exc);
        } catch (InstantiationException exc) {
            throw new GConfLoadException(exc);
        } catch (IllegalAccessException exc) {
            throw new GConfLoadException(exc);
        }
    }

    private <T> T load(File file, Type klass) throws IOException {
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream(file), Charsets.UTF_8);
        JsonReader reader = new JsonReader(isr);

        try {
            return gson.fromJson(reader, klass);
        } finally {
            reader.close();
        }
    }

    public void write(String filename, Object object)
            throws GConfLoadException {
        try {
            write(getFile(filename), object);
        } catch (IOException exc) {
            throw new GConfLoadException(exc);
        }
    }

    private void write(File file, Object object) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(file), Charsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        try {
            writer.write(gson.toJson(object));
        } finally {
            writer.close();
        }
    }
}
