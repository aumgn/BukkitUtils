package fr.aumgn.bukkitutils.itemtype;

import java.util.Locale;

public class ColorDataParser extends ItemTypeDataParser {

    private final boolean dye;

    public ColorDataParser(boolean dye) {
        this.dye = dye;
    }

    private Short parseColor(String token) {
        String lToken = token.toLowerCase(Locale.ENGLISH);
        if (lToken.equals("white")) {
            return 0;
        }
        if (lToken.equals("orange")) {
            return 1;
        }
        if (lToken.equals("purple")) {
            return 2;
        }
        if (lToken.equals("lightblue")) {
            return 3;
        }
        if (lToken.equals("yellow")) {
            return 4;
        }
        if (lToken.equals("green")) {
            return 5;
        }
        if (lToken.equals("pink")
                || lToken.equalsIgnoreCase("rose")) {
            return 6;
        }
        if (lToken.equals("darkgray")) {
            return 7;
        }
        if (lToken.equals("gray")) {
            return 8;
        }
        if (lToken.equals("cyan")) {
            return 9;
        }
        if (lToken.equals("darkpurple")) {
            return 10;
        }
        if (lToken.equals("blue")) {
            return 11;
        }
        if (lToken.equals("brown")) {
            return 12;
        }
        if (lToken.equals("darkgreen")) {
            return 13;
        }
        if (lToken.equals("red")) {
            return 14;
        }
        if (lToken.equals("black")) {
            return 15;
        }

        return null;
    }

    @Override
    public Short parse(String token) {
        Short data = parseColor(token);

        if (data != null && dye) {
            return (short) (15 - data);
        } else {
            return data;
        }
    }

}
