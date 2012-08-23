package fr.aumgn.bukkitutils.gson;

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

public class GsonLoader {

    private final Gson gson;
    private final Plugin plugin;

    public GsonLoader(Gson gson, Plugin plugin) {
        this.gson = gson;
        this.plugin = plugin;
    }

    private File getFile(String filename)
            throws GsonLoadException {
        File file = new File(plugin.getDataFolder(), filename);
        if (!file.getParentFile().mkdirs()) {
            throw new GsonLoadException("Impossible de créer le dossier :"
                    + plugin.getDataFolder().getPath());
        }

        return file;
    }

    public <T> T loadOrCreate(String filename, Class<T> klass)
            throws GsonLoadException {
        return loadOrCreate(filename, klass, klass);
    }

    @SuppressWarnings("unchecked")
    public <T> T loadOrCreate(String filename, TypeToken<T> typeToken)
            throws GsonLoadException {
        return (T) loadOrCreate(filename, typeToken.getType(),
                typeToken.getRawType());
    }

    private <T> T loadOrCreate(String filename, Type type, Class<T> klass)
            throws GsonLoadException {
        try {
            File file = getFile(filename);
            T instance;
            if (file.createNewFile()) {
                instance = klass.newInstance();
            } else {
                instance = unsafeLoad(file, type);
            }

            // This ensures user file is updated with newer fields.
            write(file, instance);

            return instance;
        } catch (IOException exc) {
            throw new GsonLoadException(exc);
        } catch (InstantiationException exc) {
            throw new GsonLoadException(exc);
        } catch (IllegalAccessException exc) {
            throw new GsonLoadException(exc);
        }
    }

    public <T> T load(File file, Type klass) throws GsonLoadException {
        try {
            return unsafeLoad(file, klass);
        } catch (IOException exc) {
            throw new GsonLoadException(exc);
        }
    }

    public <T> T load(File file, TypeToken<T> typeToken)
            throws GsonLoadException {
        try {
            return unsafeLoad(file, typeToken.getRawType());
        } catch (IOException exc) {
            throw new GsonLoadException(exc);
        }
    }

    private <T> T unsafeLoad(File file, Type klass) throws IOException {
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream(file), Charsets.UTF_8);
        JsonReader reader = new JsonReader(isr);

        try {
            return gson.fromJson(reader, klass);
        } finally {
            reader.close();
        }
    }

    public void write(String filename, Object instance)
            throws GsonLoadException {
        try {
            write(getFile(filename), instance);
        } catch (IOException exc) {
            throw new GsonLoadException(exc);
        }
    }

    private void write(File file, Object instance) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(file), Charsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        try {
            writer.write(gson.toJson(instance));
        } finally {
            writer.close();
        }
    }
}
