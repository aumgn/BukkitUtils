package fr.aumgn.bukkitutils.command.args;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import fr.aumgn.bukkitutils.command.Messages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandListArg;
import fr.aumgn.bukkitutils.command.arg.basic.DoubleArg;
import fr.aumgn.bukkitutils.command.arg.basic.IntegerArg;
import fr.aumgn.bukkitutils.command.arg.basic.Vector2DArg;
import fr.aumgn.bukkitutils.command.arg.basic.VectorArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.ChatColorArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.EnchantmentArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.EntityTypeArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.ItemTypeArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.MaterialArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.OfflinePlayerArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.PlayerArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.PotionEffectTypeArg;
import fr.aumgn.bukkitutils.command.arg.bukkit.WorldArg;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;
import fr.aumgn.bukkitutils.itemtype.ItemType;

public class CommandArgs extends CommandArgsBase {

    public CommandArgs(Messages messages, CommandArgsParser parser) {
        super(messages, parser);
    }

    public CommandArg<Integer> getInteger(int index) {
        return get(index, new IntegerArg.Factory());
    }

    public CommandArg<Double> getDouble(int index) {
        return get(index, new DoubleArg.Factory());
    }

    public CommandArg<ChatColor> getChatColor(int index) {
        return get(index, new ChatColorArg.Factory());
    }

    public CommandArg<Vector> getVector(int index) {
        return get(index, new VectorArg.Factory());
    }

    public CommandArg<Vector2D> getVector2D(int index) {
        return get(index, new Vector2DArg.Factory());
    }

    public CommandArg<World> getWorld(int index) {
        return get(index, new WorldArg.Factory());
    }

    public CommandArg<Player> getPlayer(int index) {
        return get(index, new PlayerArg.Factory());
    }

    public CommandListArg<Player> getPlayers(int index) {
        return getList(index, new PlayerArg.Factory());
    }

    public CommandArg<OfflinePlayer> getOfflinePlayer(int index) {
        return get(index, new OfflinePlayerArg.Factory());
    }

    public CommandArg<Material> getMaterial(int index) {
        return get(index, new MaterialArg.Factory());
    }

    public CommandArg<ItemType> getItemType(int index) {
        return get(index, new ItemTypeArg.Factory());
    }

    public CommandArg<PotionEffectType> getPotionEffectType(int index) {
        return get(index, new PotionEffectTypeArg.Factory());
    }

    public CommandArg<Enchantment> getEnchantment(int index) {
        return get(index, new EnchantmentArg.Factory());
    }

    public CommandArg<EntityType> getEntityType(int index) {
        return get(index, new EntityTypeArg.Factory());
    }

    public CommandArg<Integer> getInteger(char flag) {
        return get(flag, new IntegerArg.Factory());
    }

    public CommandArg<Double> getDouble(char flag) {
        return get(flag, new DoubleArg.Factory());
    }

    public CommandArg<ChatColor> getChatColor(char flag) {
        return get(flag, new ChatColorArg.Factory());
    }

    public CommandArg<Vector> getVector(char flag) {
        return get(flag, new VectorArg.Factory());
    }

    public CommandArg<Vector2D> getVector2D(char flag) {
        return get(flag, new Vector2DArg.Factory());
    }

    public CommandArg<World> getWorld(char flag) {
        return get(flag, new WorldArg.Factory());
    }

    public CommandArg<Player> getPlayer(char flag) {
        return get(flag, new PlayerArg.Factory());
    }

    public CommandListArg<Player> getPlayers(char flag) {
        return getList(flag, new PlayerArg.Factory());
    }

    public CommandArg<OfflinePlayer> getOfflinePlayer(char flag) {
        return get(flag, new OfflinePlayerArg.Factory());
    }

    public CommandArg<Material> getMaterial(char flag) {
        return get(flag, new MaterialArg.Factory());
    }

    public CommandArg<ItemType> getItemType(char flag) {
        return get(flag, new ItemTypeArg.Factory());
    }

    public CommandArg<PotionEffectType> getPotionEffectType(char flag) {
        return get(flag, new PotionEffectTypeArg.Factory());
    }

    public CommandArg<Enchantment> getEnchantment(char flag) {
        return get(flag, new EnchantmentArg.Factory());
    }

    public CommandArg<EntityType> getEntityType(char flag) {
        return get(flag, new EntityTypeArg.Factory());
    }
}
