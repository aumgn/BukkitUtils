package fr.aumgn.bukkitutils.localization;

import com.google.common.base.Charsets;
import fr.aumgn.bukkitutils.localization.loaders.JsonMessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.MessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.PropertiesMessagesLoader;
import fr.aumgn.bukkitutils.localization.loaders.YamlMessagesLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.*;

/**
 * Class which handles loading of localization
 * resources for the given plugin and locale.
 * <p/>
 * This class looks for resources in the plugin's jarfile
 * (in the plugin's package) and in the plugin's data folder.
 * <p/>
 * Resources are defined by a name to which is appended
 * a suffix corresponding to the locale.
 * <p/>
 * For example if you're looking for a resource with the
 * name "messages" for the plugin MyPlugin whose main class
 * is "fr.aumgn.myplugin.MyPlugin" and the locale "fr_FR",
 * the class will go through all this steps :
 * <ul>
 * <li>
 * Looks for the resource
 * fr/aumgn/myplugin/messages_en.{json,yml,properties}
 * in the jarfile
 * </li>
 * <li>
 * Looks for the resource
 * [server]/plugins/MyPlugin/messages_en.{json,yml,properties}
 * </li>
 * <li>
 * Looks for the resource
 * fr/aumgn/myplugin/messages_en_US.{json,yml,properties}
 * in the jarfile
 * </li>
 * <li>
 * Looks for the resource
 * [server]/plugins/MyPlugin/messages_en_US.{json,yml,properties}
 * </li>
 * <li>
 * Looks for the resource
 * fr/aumgn/myplugin/messages_fr.{json,yml,properties}
 * in the jarfile
 * </li>
 * <li>
 * Looks for the resource
 * [server]/plugins/MyPlugin/messages_fr.{json,yml,properties}
 * </li>
 * <li>
 * Looks for the resource
 * fr/aumgn/myplugin/messages_fr_FR.{json,yml,properties}
 * in the jarfile
 * </li>
 * <li>
 * Looks for the resource
 * [server]/plugins/MyPlugin/messages_fr_FR.{json,yml,properties}
 * </li>
 * </ul>
 * <p/>
 * Each values find in a loaded resources has priority
 * over values find in previous resources.
 */
public class Localization {

    public static final Locale DEFAULT_LOCALE = Locale.US;

    private static final Deque<MessagesLoader> loaders =
            new ArrayDeque<MessagesLoader>();

    static {
        register(new PropertiesMessagesLoader());
        register(new YamlMessagesLoader());
        register(new JsonMessagesLoader());
    }

    protected final JavaPlugin plugin;
    protected final Locale[] locales;
    private final File dir;

    public Localization(JavaPlugin plugin) {
        this(plugin, DEFAULT_LOCALE);
    }
    public Localization(JavaPlugin plugin, Locale targetLocale) {
        this(plugin, plugin.getDataFolder(), targetLocale);
    }
    public Localization(JavaPlugin plugin, Locale targetLocale,
                        Locale fallbackLocale) {
        this(plugin, plugin.getDataFolder(), targetLocale, fallbackLocale);
    }

    public Localization(JavaPlugin plugin, File dir, Locale targetLocale) {
        this(plugin, dir, targetLocale, DEFAULT_LOCALE);
    }

    public Localization(JavaPlugin plugin, File dir, Locale targetLocale,
                        Locale fallbackLocale) {
        this.plugin = plugin;
        this.dir = dir;
        this.locales = localesLookupFor(targetLocale, fallbackLocale);
    }

    /**
     * Registers a MessagesLoader.
     */
    public static void register(MessagesLoader loader) {
        loaders.push(loader);
    }

    protected static Iterable<MessagesLoader> loaders() {
        return new Iterable<MessagesLoader>() {
            @Override
            public Iterator<MessagesLoader> iterator() {
                return loaders.descendingIterator();
            }
        };
    }

    public static Locale[] localesLookupFor(Locale targetLocale,
                                            Locale fallbackLocale) {
        Set<Locale> localesSet = new LinkedHashSet<Locale>(4);
        localesSet.add(targetLocale);
        localesSet.add(new Locale(targetLocale.getLanguage()));
        localesSet.add(fallbackLocale);
        localesSet.add(new Locale(fallbackLocale.getLanguage()));
        Locale[] locales = new Locale[localesSet.size()];
        int i = locales.length - 1;
        for (Locale locale : localesSet) {
            locales[i] = locale;
            i--;
        }
        return locales;
    }

    /**
     * Loads the resources with the given name.
     */
    public PluginMessages get(String name) {
        return new PluginMessages(loadMap(name));
    }

    protected Map<String, MessageFormat> loadMap(String name) {
        Map<String, MessageFormat> map = new HashMap<String, MessageFormat>();
        for (Locale locale : locales) {
            load(map, locale, name + "_" + locale.toString());
        }
        return map;
    }

    protected void load(Map<String, MessageFormat> map, Locale locale,
                        String name) {
        loadBundled(map, locale, name);
        loadUser(map, locale, name);
    }

    protected void loadStream(Map<String, MessageFormat> map, Locale locale,
                              MessagesLoader loader, InputStream iStream) {
        Reader reader =
                new InputStreamReader(iStream, Charsets.UTF_8);
        map.putAll(loader.load(locale, reader));
        try {
            reader.close();
        }
        catch (IOException exc) {
        }
    }

    private void loadBundled(Map<String, MessageFormat> map, Locale locale,
                             String baseName) {
        if (plugin == null) {
            return;
        }

        for (MessagesLoader loader : loaders()) {
            for (String extension : loader.getExtensions()) {
                String name = baseName + "." + extension;

                InputStream iStream = null;
                URL res = plugin.getClass().getResource(name);
                if (res != null) {
                    try {
                        URLConnection connection = res.openConnection();
                        connection.setUseCaches(false);
                        iStream = connection.getInputStream();
                    }
                    catch (IOException exc) {
                    }
                }

                if (iStream != null) {
                    loadStream(map, locale, loader, iStream);
                    return;
                }
            }
        }
    }

    private void loadUser(Map<String, MessageFormat> map, Locale locale,
                          String baseName) {
        if (dir == null) {
            return;
        }

        for (MessagesLoader loader : loaders()) {
            for (String extension : loader.getExtensions()) {
                String name = baseName + "." + extension;

                File file = new File(dir, name);
                if (file.exists()) {
                    try {
                        InputStream iStream = new FileInputStream(file);
                        loadStream(map, locale, loader, iStream);
                    }
                    catch (IOException exc) {
                    }
                }
            }
        }
    }
}
