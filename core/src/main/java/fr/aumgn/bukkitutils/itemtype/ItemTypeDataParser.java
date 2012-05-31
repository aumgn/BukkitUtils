package fr.aumgn.bukkitutils.itemtype;

import org.bukkit.Material;

public abstract class ItemTypeDataParser {

    public static ItemTypeDataParser getFor(Material material) {
        if (material == Material.WOOL) {
            return new ColorDataParser(false);
        }

        if (material == Material.INK_SACK) {
            return new ColorDataParser(true);
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

        return null;
    }

    public abstract Short parse(String token);
}
