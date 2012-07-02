package fr.aumgn.bukkitutils.localization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PluginResourceBundleControl extends ResourceBundle.Control {

    private final File resourcesFolder;

    public PluginResourceBundleControl(File resourcesFolder) {
        super();
        this.resourcesFolder = resourcesFolder;
    }

    @Override
    public List<String> getFormats(String baseName) {
        List<String> list = new ArrayList<String>();
        list.add("plugin.properties");
        list.addAll(super.getFormats(baseName));

        return list;
    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException, IllegalAccessException, InstantiationException {
        if (!format.equals("plugin.properties")) {
            return super.newBundle(baseName, locale, format, loader, reload);
        }

        String bundleName = toBundleName(baseName, locale);
        File filename = new File(resourcesFolder, bundleName + ".properties");
        if (!filename.exists()) {
            return null;
        }

        FileReader reader = new FileReader(filename);
        return new PropertyResourceBundle(reader);
    }
}
