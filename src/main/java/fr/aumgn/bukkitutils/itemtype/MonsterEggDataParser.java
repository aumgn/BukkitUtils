package fr.aumgn.bukkitutils.itemtype;

import org.bukkit.entity.EntityType;

public class MonsterEggDataParser extends ItemTypeDataParser {

    @Override
    public Short parse(String token) {
        EntityType entityType = EntityType.fromName(token);
        if (entityType != null) {
            int id = entityType.getTypeId();
            if (validate(id)) {
                return (short) id; 
            }
        }

        return null;
    }

    private boolean validate(int id) {
        if (id >= 50 && id <= 52) {
            return true;
        }

        if (id >= 54 && id <= 62) {
            return true;
        }

        if (id >= 90 && id <= 98) {
            return true;
        }

        return (id == 98 || id == 120);
    }
}
