package fr.aumgn.bukkitutils.command;

import fr.aumgn.bukkitutils.localization.Localization;
import fr.aumgn.bukkitutils.localization.loaders.MessagesLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

public class CommandsLocalization extends Localization {

    /**
     * Do not use, only needed for testing purposes !
     */
    public CommandsLocalization(Locale locale) {
        super(null, null, locale, locale);
    }

    public CommandsLocalization(JavaPlugin plugin, Locale locale) {
        super(plugin, locale);
    }

    @Override
    public CommandsMessages get(String name) {
        return new CommandsMessages(loadMap(name));
    }

    @Override
    protected void load(Map<String, MessageFormat> map, Locale locale,
                        String name) {
        loadInJar(map, locale, name);
        super.load(map, locale, name);
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
                    }
                    catch (IOException _) {
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
