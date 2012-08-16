package fr.aumgn.bukkitutils.itemtype;

public class MonsterEggsDataParser extends ItemTypeDataParser {

    @Override
    public Short parse(String token) {
        if (token.equals("stone")) {
            return 0;
        }
        if (token.equals("cobblestone")) {
            return 1;
        }
        if (token.equals("smooth_brick")) {
            return 2;
        }

        return null;
    }
}
