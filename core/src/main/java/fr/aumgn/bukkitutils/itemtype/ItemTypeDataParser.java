package fr.aumgn.bukkitutils.itemtype;

import org.bukkit.Material;

public class ItemTypeDataParser {

    public static ItemTypeDataParser getFor(Material material) {
        if (material == Material.WOOL) {
            return new WoolDataParser();
        }
        if (material == Material.WOOD
                || material == Material.SAPLING
                || material == Material.LOG
                || material == Material.LEAVES) {
            return new WoodDataParser();
        }
        if (material == Material.DOUBLE_STEP
                || material == Material.STEP) {
            return new StepDataParser();
        }
        if (material == Material.SMOOTH_BRICK) {
            return new SmoothBrickDataParser();
        }

        return new ItemTypeDataParser();
    }

    public Byte parse(String token) {
        try {
            return Byte.parseByte(token);
        } catch (NumberFormatException exc) {
            return null;
        }
 
    }
}
