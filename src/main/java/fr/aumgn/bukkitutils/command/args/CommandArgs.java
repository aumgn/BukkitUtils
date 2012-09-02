package fr.aumgn.bukkitutils.command.args;

import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.basic.ListArg;
import fr.aumgn.bukkitutils.command.arg.basic.DoubleArg;
import fr.aumgn.bukkitutils.command.arg.basic.IntegerArg;
import fr.aumgn.bukkitutils.command.arg.basic.ShortArg;
import fr.aumgn.bukkitutils.command.arg.basic.TimeArg;
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

public class CommandArgs extends CommandArgsBase {

    public CommandArgs(CommandsMessages messages, CommandArgsParser parser) {
        super(messages, parser);
    }

    public IntegerArg getInteger(int index) {
        return new IntegerArg(messages, getOrNull(index));
    }

    public ShortArg getShort(int index) {
        return new ShortArg(messages, getOrNull(index));
    }

    public DoubleArg getDouble(int index) {
        return new DoubleArg(messages, getOrNull(index));
    }

    public TimeArg getTime(int index) {
        return new TimeArg(messages, getOrNull(index));
    }

    public ChatColorArg getChatColor(int index) {
        return new ChatColorArg(messages, getOrNull(index));
    }

    public VectorArg getVector(int index) {
        return new VectorArg(messages, getOrNull(index));
    }

    public Vector2DArg getVector2D(int index) {
        return new Vector2DArg(messages, getOrNull(index));
    }

    public WorldArg getWorld(int index) {
        return new WorldArg(messages, getOrNull(index));
    }

    public PlayerArg getPlayer(int index) {
        return new PlayerArg(messages, getOrNull(index));
    }

    public ListArg<Player, PlayerArg> getPlayers(int index) {
        return getList(index, new CommandArgFactory<PlayerArg>() {
            public PlayerArg createCommandArg(String string) {
                return new PlayerArg(messages, string);
            }
        });
    }

    public OfflinePlayerArg getOfflinePlayer(int index) {
        return new OfflinePlayerArg(messages, getOrNull(index));
    }

    public MaterialArg getMaterial(int index) {
        return new MaterialArg(messages, getOrNull(index));
    }

    public ItemTypeArg getItemType(int index) {
        return new ItemTypeArg(messages, getOrNull(index));
    }

    public PotionEffectTypeArg getPotionEffectType(int index) {
        return new PotionEffectTypeArg(messages, getOrNull(index));
    }

    public EnchantmentArg getEnchantment(int index) {
        return new EnchantmentArg(messages, getOrNull(index));
    }

    public EntityTypeArg getEntityType(int index) {
        return new EntityTypeArg(messages, getOrNull(index));
    }

    public IntegerArg getInteger(char flag) {
        return new IntegerArg(messages, getOrNull(flag));
    }

    public ShortArg getShort(char flag) {
        return new ShortArg(messages, getOrNull(flag));
    }

    public DoubleArg getDouble(char flag) {
        return new DoubleArg(messages, getOrNull(flag));
    }

    public TimeArg getTime(char flag) {
        return new TimeArg(messages, getOrNull(flag));
    }

    public ChatColorArg getChatColor(char flag) {
        return new ChatColorArg(messages, getOrNull(flag));
    }

    public VectorArg getVector(char flag) {
        return new VectorArg(messages, getOrNull(flag));
    }

    public Vector2DArg getVector2D(char flag) {
        return new Vector2DArg(messages, getOrNull(flag));
    }

    public WorldArg getWorld(char flag) {
        return new WorldArg(messages, getOrNull(flag));
    }

    public PlayerArg getPlayer(char flag) {
        return new PlayerArg(messages, getOrNull(flag));
    }

    public ListArg<Player, PlayerArg> getPlayers(char flag) {
        return getList(flag, new CommandArgFactory<PlayerArg>() {
            public PlayerArg createCommandArg(String string) {
                return new PlayerArg(messages, string);
            }
        });
    }

    public OfflinePlayerArg getOfflinePlayer(char flag) {
        return new OfflinePlayerArg(messages, getOrNull(flag));
    }

    public MaterialArg getMaterial(char flag) {
        return new MaterialArg(messages, getOrNull(flag));
    }

    public ItemTypeArg getItemType(char flag) {
        return new ItemTypeArg(messages, getOrNull(flag));
    }

    public PotionEffectTypeArg getPotionEffectType(char flag) {
        return new PotionEffectTypeArg(messages, getOrNull(flag));
    }

    public EnchantmentArg getEnchantment(char flag) {
        return new EnchantmentArg(messages, getOrNull(flag));
    }

    public EntityTypeArg getEntityType(char flag) {
        return new EntityTypeArg(messages, getOrNull(flag));
    }
}
