package fr.aumgn.bukkitutils.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MaterialAndData {

    private final Material material;
    private final byte data;

    public MaterialAndData(Material material) {
        this(material, 0);
    }

    public MaterialAndData(Material material, int data) {
        this(material, (byte) data);
    }

    public MaterialAndData(Material material, byte data) {
        if (material == null) {
            throw new IllegalArgumentException("Material can't be null.");
        }

        this.material = material;
        this.data = data;
    }

    public MaterialAndData(ItemStack stack) {
        this(stack.getType(), stack.getData().getData());
    }

    public MaterialAndData(Block block) {
        this(block.getType(), block.getData());
    }

    public Material getMaterial() {
        return material;
    }

    public MaterialAndData setMaterial(Material material) {
        return new MaterialAndData(material, data);
    }

    public byte getData() {
        return data;
    }

    public MaterialAndData setData(byte data) {
        return new MaterialAndData(material, data);
    }

    public int getMaxStackSize() {
        return material.getMaxStackSize();
    }

    public int getMaxDurability() {
        return material.getMaxDurability();
    }

    public ItemStack toItemStack() {
        return toItemStack(1);
    }

    public ItemStack toMaxItemStack() {
        return toItemStack(getMaxStackSize());
    }

    public ItemStack toItemStack(int amount) {
        return toItemStack(amount, 0);
    }

    public ItemStack toItemStack(int amount, int damage) {
        return toItemStack(amount, (short) damage);
    }

    public ItemStack toItemStack(int amount, short damage) {
        return new ItemStack(material, amount, damage, data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + data;
        result = prime * result + material.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MaterialAndData)) {
            return false;
        }

        MaterialAndData other = (MaterialAndData) obj;
        if (data != other.data || material != other.material) {
            return false;
        }

        return true;
    }

    public String toString() {
        return "(" + material + ":" + data + ")";
    }
}
