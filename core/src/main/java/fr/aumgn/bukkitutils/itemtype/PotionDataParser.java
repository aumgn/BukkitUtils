package fr.aumgn.bukkitutils.itemtype;

import java.util.Locale;

import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class PotionDataParser extends ItemTypeDataParser {

    @Override
    public Short parse(String rawToken) {
        String token = rawToken;
        PotionType type = null;
        int level = 1;
        boolean splash = false;
        boolean extended = false;

        if (token.charAt(token.length() - 1) == '$') {
            splash = true;
            token = token.substring(0, token.length() - 1);
        }

        if (token.charAt(token.length() - 1) == '#') {
            extended = true;
            token = token.substring(0, token.length() - 1);
        }

        char lastChar = token.charAt(token.length() - 1);
        if (Character.isDigit(lastChar)) {
            try {
                level = Integer.parseInt(String.valueOf(lastChar));
            } catch (NumberFormatException exc) {
            }
            token = token.substring(0, token.length() - 1);
        }

        try {
            type = PotionType.valueOf(token.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException exc) {
            return null;
        }

        if (level < 1) {
            level = 1;
        } else if (level > type.getMaxLevel()) {
            level = type.getMaxLevel();
        }

        Potion potion = new Potion(type, level);
        if (splash) {
            potion = potion.splash();
        }
        if (extended && !type.isInstant()) {
            potion = potion.extend();
        }

        return potion.toDamageValue();
    }
}
