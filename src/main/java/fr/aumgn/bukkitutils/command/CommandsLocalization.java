package fr.aumgn.bukkitutils.command;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import fr.aumgn.bukkitutils.localization.Localization;
import fr.aumgn.bukkitutils.localization.loaders.MessagesLoader;

class CommandsLocalization extends Localization {

    public CommandsLocalization(Locale locale) {
        super(null, locale, null);
    }

    public CommandsLocalization(JavaPlugin plugin, Locale locale) {
        super(plugin, locale);
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
                InputStream iStream = getClass()
                        .getResourceAsStream(name);
                if (iStream != null) {
                    loadStream(map, locale, loader, iStream);
                    return;
                }
            }
        }
    }
}
