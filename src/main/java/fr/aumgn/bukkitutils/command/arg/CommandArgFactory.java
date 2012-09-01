package fr.aumgn.bukkitutils.command.arg;

import java.util.HashMap;
import java.util.Map;

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

public abstract class CommandArgFactory<T extends CommandArg<?>> {

    private static final Map<Class<?>, CommandArgFactory<?>> MAP;

    static {
        MAP = new HashMap<Class<?>, CommandArgFactory<?>>();
        register(IntegerArg.class, new IntegerArg.Factory());
        register(ShortArg.class, new ShortArg.Factory());
        register(DoubleArg.class, new DoubleArg.Factory());
        register(ChatColorArg.class, new ChatColorArg.Factory());
        register(VectorArg.class, new VectorArg.Factory());
        register(Vector2DArg.class, new Vector2DArg.Factory());
        register(WorldArg.class, new WorldArg.Factory());
        register(PlayerArg.class, new PlayerArg.Factory());
        register(OfflinePlayerArg.class, new OfflinePlayerArg.Factory());
        register(MaterialArg.class, new MaterialArg.Factory());
        register(ItemTypeArg.class, new ItemTypeArg.Factory());
        register(PotionEffectTypeArg.class, new PotionEffectTypeArg.Factory());
        register(EnchantmentArg.class, new EnchantmentArg.Factory());
        register(EntityTypeArg.class, new EntityTypeArg.Factory());
    }

    public static <T extends CommandArg<?>> void register(Class<T> klass,
            CommandArgFactory<T> factory) {
        MAP.put(klass, factory);
    }

    @SuppressWarnings({ "unchecked"})
    public static <T extends CommandArg<?>> CommandArgFactory<T> get(Class<T> klass) {
        CommandArgFactory<T> factory = (CommandArgFactory<T>) MAP.get(klass);
        if (factory != null) {
            return factory;
        }

        return null;
    }

    public abstract T createCommandArg(
            CommandsMessages messages, String string);
}
