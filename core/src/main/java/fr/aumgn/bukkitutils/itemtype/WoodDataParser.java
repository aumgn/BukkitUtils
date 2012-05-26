package fr.aumgn.bukkitutils.itemtype;

import java.util.Locale;

public class WoodDataParser extends ItemTypeDataParser {

    @Override
    public Byte parse(String token) {
        String lToken = token.toLowerCase(Locale.ENGLISH);
        if (lToken.equals("oak")) {
            return 0;
        }
        if (lToken.equals("pine")
                || lToken.equals("spruce")) {
            return 1;
        }
        if (lToken.equals("birch")) {
            return 2;
        }
        if (lToken.equals("jungle")) {
            return 3;
        }

        return super.parse(token);
    }
}
