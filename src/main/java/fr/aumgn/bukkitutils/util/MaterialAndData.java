package fr.aumgn.bukkitutils.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialAndData {

    private Material material;
    private byte data;

    public MaterialAndData(Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public ItemStack toItemStack(int amount, short damage) {
        return new ItemStack(material, amount, damage, data);
    }
}
