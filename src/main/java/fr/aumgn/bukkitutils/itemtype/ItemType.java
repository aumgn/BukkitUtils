package fr.aumgn.bukkitutils.itemtype;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ItemType {

    private final Material material;
    private final short data;

    public ItemType(Material material) {
        this(material, 0);
    }

    public ItemType(Material material, int data) {
        this(material, (short) data);
    }

    public ItemType(Material material, short data) {
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

    public short getData() {
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

    public ItemStack toMaxItemStack() {
        return toItemStack(getMaxStackSize());
    }

    public ItemStack toItemStack() {
        return toItemStack(1);
    }

    public ItemStack toItemStack(int amount) {
        return new ItemStack(material, amount, data);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(31, 13)
            .append(data)
            .append(material)
            .toHashCode();
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
