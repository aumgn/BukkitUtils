package fr.aumgn.bukkitutils.itemtype;

import java.util.Locale;

public class SmoothBrickDataParser extends ItemTypeDataParser {

    @Override
    public Byte parse(String token) {
        String lToken = token.toLowerCase(Locale.ENGLISH);
        if (lToken.equals("cracked")) {
            return 1;
        }
        if (lToken.equals("mossy")) {
            return 2;
        }
        if (lToken.equals("chiseled")
                || lToken.equals("round")) {
            return 3;
        }

        return null;
    }

}
