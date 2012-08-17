package fr.aumgn.bukkitutils.itemtype;

public class MonsterEggsDataParser extends ItemTypeDataParser {

    @Override
    public Short parse(String token) {
        if (token.equalsIgnoreCase("stone")) {
            return 0;
        }
        if (token.equalsIgnoreCase("cobblestone")) {
            return 1;
        }
        if (token.equalsIgnoreCase("smooth_brick")) {
            return 2;
        }

        return null;
    }
}
