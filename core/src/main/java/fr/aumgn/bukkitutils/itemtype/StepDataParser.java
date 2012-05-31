package fr.aumgn.bukkitutils.itemtype;

import java.util.Locale;

public class StepDataParser extends ItemTypeDataParser {

    @Override
    public Byte parse(String token) {
        String lToken = token.toLowerCase(Locale.ENGLISH);
        if (lToken.equals("stone")) {
            return 0;
        }
        if (lToken.equals("sandstone")) {
            return 1;
        }
        if (lToken.equals("wood")) {
            return 2;
        }
        if (lToken.equals("cobblestone")) {
            return 3;
        }
        if (lToken.equals("brick")) {
            return 4;
        }
        if (lToken.equals("stonebrick")) {
            return 5;
        }

        return null;
    }
}
