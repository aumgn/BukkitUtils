package fr.aumgn.bukkitutils.localization;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginResourceBundles {

    private final JavaPlugin plugin;
    private final Locale locale;
    private final File resourcesFolder;

    public PluginResourceBundles(JavaPlugin plugin, Locale locale, File resourcesFolder) {
        this.plugin = plugin;
        this.locale = locale;
        this.resourcesFolder = resourcesFolder;
    }

    public <T extends JavaPlugin & Localizable> PluginResourceBundles(T plugin) {
        this(plugin, plugin.getLocale(), plugin.getResourcesFolder());
    }

    public ResourceBundle get(String name) {
        return ResourceBundle.getBundle(
                name, locale,
                plugin.getClass().getClassLoader(),
                new PluginResourceBundleControl(plugin, resourcesFolder));
    }
}
