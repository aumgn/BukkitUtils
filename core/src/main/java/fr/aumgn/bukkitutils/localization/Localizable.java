package fr.aumgn.bukkitutils.localization;

import java.io.File;
import java.util.Locale;

public interface Localizable {

    Locale getLocale();

    File getResourcesFolder();
}
