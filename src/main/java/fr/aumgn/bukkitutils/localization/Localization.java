package fr.aumgn.bukkitutils.localization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;

import fr.aumgn.bukkitutils.localization.loaders.JsonMessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.MessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.PropertiesMessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.YamlMessagesLoader;


public class Localization {

    private static final Locale DEFAULT_LOCALE = Locale.US;

    private static final Deque<MessagesLoader> loaders =
            new ArrayDeque<MessagesLoader>();

    public static void register(MessagesLoader loader) {
        loaders.push(loader);
    }

    public static Iterable<MessagesLoader> loaders() {
        return new Iterable<MessagesLoader>() {
            @Override
            public Iterator<MessagesLoader> iterator() {
                return loaders.descendingIterator();
            }
        };
    }

    static {
        register(new PropertiesMessagesLoader());
        register(new YamlMessagesLoader());
        register(new JsonMessagesLoader());
    }

    protected final JavaPlugin plugin;
    protected final File dir;
    protected final Locale locale;

    public Localization(JavaPlugin plugin) {
        this(plugin, DEFAULT_LOCALE);
    }

    public Localization(JavaPlugin plugin, Locale locale) {
        this(plugin, locale, plugin.getDataFolder());
    }

    public Localization(JavaPlugin plugin, Locale locale, File dir) {
        this.plugin = plugin;
        this.locale = locale;
        this.dir = dir;
    }

    public PluginMessages get(String name) {
        Map<String, MessageFormat> map = new HashMap<String, MessageFormat>();
        loadAll(name, map, DEFAULT_LOCALE);
        loadAll(name, map, locale);
        return new PluginMessages(map);
    }

    protected void loadAll(String name, Map<String, MessageFormat> map,
            Locale locale) {
        loadBundled(map, locale, name + "_" + locale.getLanguage());
        if (!locale.getCountry().isEmpty()) {
            loadBundled(map, locale, name + "_" + locale.toString());
        }

        loadUser(map, locale, name + "_" + locale.getLanguage());
        if (!locale.getCountry().isEmpty()) {
            loadUser(map, locale, name + "_" + locale.toString());
        }
    }

    protected void loadStream(Map<String, MessageFormat> map, Locale locale,
            MessagesLoader loader, InputStream iStream) {
        Reader reader =
                new InputStreamReader(iStream, Charsets.UTF_8);
        map.putAll(loader.load(locale, reader));
        try {
            reader.close();
        } catch (IOException exc) {
        }
    }

    private void loadBundled(Map<String, MessageFormat> map,  Locale locale,
            String baseName) {
        for (MessagesLoader loader : loaders()) {
            for (String extension : loader.getExtensions()) {
                String name = baseName + "." + extension;
                InputStream iStream = plugin.getClass()
                        .getResourceAsStream(name);
                System.out.print(plugin.getClass().getResource(name));
                if (iStream != null) {
                    loadStream(map, locale, loader, iStream);
                    return;
                }
            }
        }
    }

    private void loadUser(Map<String, MessageFormat> map, Locale locale,
            String baseName) {
        for (MessagesLoader loader : loaders()) {
            for (String extension : loader.getExtensions()) {
                String name = baseName + "." + extension;

                File file = new File(dir, name);
                if (file.exists()) {
                    try {
                        InputStream iStream = new FileInputStream(file);
                        loadStream(map, locale, loader, iStream);
                    } catch (IOException exc) {
                    }
                }
            }
        }
    }
}
