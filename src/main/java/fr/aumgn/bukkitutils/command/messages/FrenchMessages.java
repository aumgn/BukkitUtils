package fr.aumgn.bukkitutils.command.messages;

import java.util.Locale;
import java.util.ResourceBundle;

@Deprecated
public class FrenchMessages extends Messages {

    public FrenchMessages() {
        super(ResourceBundle.getBundle("commands", Locale.FRENCH));
    }
}
