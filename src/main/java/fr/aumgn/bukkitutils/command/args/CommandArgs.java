package fr.aumgn.bukkitutils.command.args;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.arg.basic.*;
import fr.aumgn.bukkitutils.command.arg.bukkit.*;
import fr.aumgn.bukkitutils.itemtype.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class CommandArgs extends CommandArgsBase {

    public CommandArgs(CommandsMessages messages, CommandArgsParser parser) {
        super(messages, parser);
    }

    public IntegerArg getInteger(int index) {
        return new IntegerArg(messages, getOrNull(index));
    }

    public IntegerArg getInteger(char flag) {
        return new IntegerArg(messages, getOrNull(flag));
    }

    public ShortArg getShort(int index) {
        return new ShortArg(messages, getOrNull(index));
    }

    public ShortArg getShort(char flag) {
        return new ShortArg(messages, getOrNull(flag));
    }

    public DoubleArg getDouble(int index) {
        return new DoubleArg(messages, getOrNull(index));
    }

    public DoubleArg getDouble(char flag) {
        return new DoubleArg(messages, getOrNull(flag));
    }

    public TimeArg getTime(int index) {
        return new TimeArg(messages, getOrNull(index));
    }

    public TimeArg getTime(char flag) {
        return new TimeArg(messages, getOrNull(flag));
    }

    public ChatColorArg getChatColor(int index) {
        return new ChatColorArg(messages, getOrNull(index));
    }

    public ChatColorArg getChatColor(char flag) {
        return new ChatColorArg(messages, getOrNull(flag));
    }

    public ListArg<ChatColor, ChatColorArg> getChatColors(int index) {
        return getList(index, new CommandArgFactory<ChatColorArg>() {
            public ChatColorArg createCommandArg(String string) {
                return new ChatColorArg(messages, string);
            }
        });
    }

    public ListArg<ChatColor, ChatColorArg> getChatColors(char flag) {
        return getList(flag, new CommandArgFactory<ChatColorArg>() {
            public ChatColorArg createCommandArg(String string) {
                return new ChatColorArg(messages, string);
            }
        });
    }

    public VectorArg getVector(int index) {
        return new VectorArg(messages, getOrNull(index));
    }

    public VectorArg getVector(char flag) {
        return new VectorArg(messages, getOrNull(flag));
    }

    public Vector2DArg getVector2D(int index) {
        return new Vector2DArg(messages, getOrNull(index));
    }

    public Vector2DArg getVector2D(char flag) {
        return new Vector2DArg(messages, getOrNull(flag));
    }

    public WorldArg getWorld(int index) {
        return new WorldArg(messages, getOrNull(index));
    }

    public WorldArg getWorld(char flag) {
        return new WorldArg(messages, getOrNull(flag));
    }

    public ListArg<World, WorldArg> getWorlds(int index) {
        return getList(index, new CommandArgFactory<WorldArg>() {
            public WorldArg createCommandArg(String string) {
                return new WorldArg(messages, string);
            }
        });
    }

    public ListArg<World, WorldArg> getWorlds(char flag) {
        return getList(flag, new CommandArgFactory<WorldArg>() {
            public WorldArg createCommandArg(String string) {
                return new WorldArg(messages, string);
            }
        });
    }

    public PlayerArg getPlayer(int index) {
        return new PlayerArg(messages, getOrNull(index));
    }

    public PlayerArg getPlayer(char flag) {
        return new PlayerArg(messages, getOrNull(flag));
    }

    public ListArg<Player, PlayerArg> getPlayers(int index) {
        return getList(index, new CommandArgFactory<PlayerArg>() {
            public PlayerArg createCommandArg(String string) {
                return new PlayerArg(messages, string);
            }
        });
    }

    public ListArg<Player, PlayerArg> getPlayers(char flag) {
        return getList(flag, new CommandArgFactory<PlayerArg>() {
            public PlayerArg createCommandArg(String string) {
                return new PlayerArg(messages, string);
            }
        });
    }

    public OfflinePlayerArg getOfflinePlayer(int index) {
        return new OfflinePlayerArg(messages, getOrNull(index));
    }

    public OfflinePlayerArg getOfflinePlayer(char flag) {
        return new OfflinePlayerArg(messages, getOrNull(flag));
    }

    public ListArg<OfflinePlayer, OfflinePlayerArg> getOfflinePlayers(
            int index) {
        return getList(index, new CommandArgFactory<OfflinePlayerArg>() {
            public OfflinePlayerArg createCommandArg(String string) {
                return new OfflinePlayerArg(messages, string);
            }
        });
    }

    public ListArg<OfflinePlayer, OfflinePlayerArg> getOfflinePlayers(
            char flag) {
        return getList(flag, new CommandArgFactory<OfflinePlayerArg>() {
            public OfflinePlayerArg createCommandArg(String string) {
                return new OfflinePlayerArg(messages, string);
            }
        });
    }

    public MaterialArg getMaterial(int index) {
        return new MaterialArg(messages, getOrNull(index));
    }

    public MaterialArg getMaterial(char flag) {
        return new MaterialArg(messages, getOrNull(flag));
    }

    public ListArg<Material, MaterialArg> getMaterials(int index) {
        return getList(index, new CommandArgFactory<MaterialArg>() {
            public MaterialArg createCommandArg(String string) {
                return new MaterialArg(messages, string);
            }
        });
    }

    public ListArg<Material, MaterialArg> getMaterials(char flag) {
        return getList(flag, new CommandArgFactory<MaterialArg>() {
            public MaterialArg createCommandArg(String string) {
                return new MaterialArg(messages, string);
            }
        });
    }

    public ItemTypeArg getItemType(int index) {
        return new ItemTypeArg(messages, getOrNull(index));
    }

    public ItemTypeArg getItemType(char flag) {
        return new ItemTypeArg(messages, getOrNull(flag));
    }

    public ListArg<ItemType, ItemTypeArg> getItemTypes(int index) {
        return getList(index, new CommandArgFactory<ItemTypeArg>() {
            public ItemTypeArg createCommandArg(String string) {
                return new ItemTypeArg(messages, string);
            }
        });
    }

    public ListArg<ItemType, ItemTypeArg> getItemTypes(char flag) {
        return getList(flag, new CommandArgFactory<ItemTypeArg>() {
            public ItemTypeArg createCommandArg(String string) {
                return new ItemTypeArg(messages, string);
            }
        });
    }

    public PotionEffectTypeArg getPotionEffectType(int index) {
        return new PotionEffectTypeArg(messages, getOrNull(index));
    }

    public PotionEffectTypeArg getPotionEffectType(char flag) {
        return new PotionEffectTypeArg(messages, getOrNull(flag));
    }

    public ListArg<PotionEffectType, PotionEffectTypeArg> getPotionEffectTypes(
            int index) {
        return getList(index, new CommandArgFactory<PotionEffectTypeArg>() {
            public PotionEffectTypeArg createCommandArg(String string) {
                return new PotionEffectTypeArg(messages, string);
            }
        });
    }

    public ListArg<PotionEffectType, PotionEffectTypeArg> getPotionEffectTypes(
            char flag) {
        return getList(flag, new CommandArgFactory<PotionEffectTypeArg>() {
            public PotionEffectTypeArg createCommandArg(String string) {
                return new PotionEffectTypeArg(messages, string);
            }
        });
    }

    public EnchantmentArg getEnchantment(int index) {
        return new EnchantmentArg(messages, getOrNull(index));
    }

    public EnchantmentArg getEnchantment(char flag) {
        return new EnchantmentArg(messages, getOrNull(flag));
    }

    public ListArg<Enchantment, EnchantmentArg> getEnchantments(int index) {
        return getList(index, new CommandArgFactory<EnchantmentArg>() {
            public EnchantmentArg createCommandArg(String string) {
                return new EnchantmentArg(messages, string);
            }
        });
    }

    public ListArg<Enchantment, EnchantmentArg> getEnchantments(char flag) {
        return getList(flag, new CommandArgFactory<EnchantmentArg>() {
            public EnchantmentArg createCommandArg(String string) {
                return new EnchantmentArg(messages, string);
            }
        });
    }

    public EntityTypeArg getEntityType(int index) {
        return new EntityTypeArg(messages, getOrNull(index));
    }

    public EntityTypeArg getEntityType(char flag) {
        return new EntityTypeArg(messages, getOrNull(flag));
    }

    public ListArg<EntityType, EntityTypeArg> getEntityTypes(int index) {
        return getList(index, new CommandArgFactory<EntityTypeArg>() {
            public EntityTypeArg createCommandArg(String string) {
                return new EntityTypeArg(messages, string);
            }
        });
    }

    public ListArg<EntityType, EntityTypeArg> getEntityTypes(char flag) {
        return getList(flag, new CommandArgFactory<EntityTypeArg>() {
            public EntityTypeArg createCommandArg(String string) {
                return new EntityTypeArg(messages, string);
            }
        });
    }
}
