package fr.aumgn.bukkitutils.command.arg;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.basic.DoubleArg;
import fr.aumgn.bukkitutils.command.arg.basic.IntegerArg;
import fr.aumgn.bukkitutils.command.arg.basic.ShortArg;
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

public abstract class CommandArgFactory<T> {

    private static final Map<Class<?>, CommandArgFactory<?>> MAP;

    static {
        MAP = new HashMap<Class<?>, CommandArgFactory<?>>();
        register(Integer.class, new IntegerArg.Factory());
        register(Double.class, new DoubleArg.Factory());
        register(ChatColor.class, new ChatColorArg.Factory());
        register(Vector.class, new VectorArg.Factory());
        register(Vector2D.class, new Vector2DArg.Factory());
        register(World.class, new WorldArg.Factory());
        register(Player.class, new PlayerArg.Factory());
        register(OfflinePlayer.class, new OfflinePlayerArg.Factory());
        register(Material.class, new MaterialArg.Factory());
        register(ItemType.class, new ItemTypeArg.Factory());
        register(PotionEffectType.class, new PotionEffectTypeArg.Factory());
        register(Enchantment.class, new EnchantmentArg.Factory());
        register(EntityType.class, new EntityTypeArg.Factory());
        register(Short.class, new ShortArg.Factory());
    }

    public static <T> void register(Class<T> klass,
            CommandArgFactory<T> factory) {
        MAP.put(klass, factory);
    }

    @SuppressWarnings("unchecked")
    public static <T> CommandArgFactory<T> get(Class<T> klass) {
        return (CommandArgFactory<T>) MAP.get(klass);
    }

    public abstract CommandArg<T> createCommandArg(
            CommandsMessages messages, String string);
}
