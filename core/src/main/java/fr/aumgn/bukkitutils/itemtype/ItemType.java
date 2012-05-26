package fr.aumgn.bukkitutils.itemtype;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ItemType {

    private final Material material;
    private final byte data;

    public ItemType(Material material) {
        this(material, 0);
    }

    public ItemType(Material material, int data) {
        this(material, (byte) data);
    }

    public ItemType(Material material, byte data) {
        if (material == null) {
            throw new IllegalArgumentException("Material can't be null.");
        }

        this.material = material;
        this.data = data;
    }

    public ItemType(ItemStack stack) {
        this(stack.getType(), stack.getData().getData());
    }

    public ItemType(Block block) {
        this(block.getType(), block.getData());
    }

    public Material getMaterial() {
        return material;
    }

    public ItemType setMaterial(Material material) {
        return new ItemType(material, data);
    }

    public byte getData() {
        return data;
    }

    public ItemType setData(byte data) {
        return new ItemType(material, data);
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

        if (!(obj instanceof ItemType)) {
            return false;
        }

        ItemType other = (ItemType) obj;
        if (data != other.data || material != other.material) {
            return false;
        }

        return true;
    }

    public String toString() {
        return "(" + material + ":" + data + ")";
    }
}
