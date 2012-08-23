package fr.aumgn.bukkitutils.command;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import fr.aumgn.bukkitutils.localization.Localization;
import fr.aumgn.bukkitutils.localization.loaders.MessagesLoader;

public class CommandsLocalization extends Localization {

    public CommandsLocalization(Locale locale) {
        super(null, locale, null);
    }

    public CommandsLocalization(JavaPlugin plugin, Locale locale) {
        super(plugin, locale);
    }

    @Override
    public CommandsMessages get(String name) {
        Map<String, MessageFormat> map = new HashMap<String, MessageFormat>();
        loadAll(name, map, DEFAULT_LOCALE);
        loadAll(name, map, locale);
        return new CommandsMessages(map);
    }

    @Override
    protected void loadAll(String name, Map<String, MessageFormat> map, Locale locale) {
        loadInJar(map, locale, name + "_" + locale.getLanguage());
        if (!locale.getCountry().isEmpty()) {
            loadInJar(map, locale, name + "_" + locale.toString());
        }
        if (plugin != null) {
            super.loadAll(name, map, locale);
        }
    }

    private void loadInJar(Map<String, MessageFormat> map, Locale locale,
            String baseName) {
        for (MessagesLoader loader : Localization.loaders()) {
            for (String extension : loader.getExtensions()) {
                String name = baseName + "." + extension;

                InputStream iStream = null;
                URL res = getClass().getResource(name);
                if (res != null) {
                    try {
                        URLConnection connection = res.openConnection();
                        connection.setUseCaches(false);
                        iStream = connection.getInputStream();
                    } catch (IOException _) {
                    }
                }

                if (iStream != null) {
                    loadStream(map, locale, loader, iStream);
                    return;
                }
            }
        }
    }
}
