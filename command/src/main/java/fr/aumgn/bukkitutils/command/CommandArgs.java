package fr.aumgn.bukkitutils.command;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.aumgn.bukkitutils.command.exception.InvalidMaterialAndDataFormat;
import fr.aumgn.bukkitutils.command.exception.NoSuchMaterial;
import fr.aumgn.bukkitutils.command.exception.NoSuchPlayer;
import fr.aumgn.bukkitutils.command.exception.NoSuchWorld;
import fr.aumgn.bukkitutils.command.messages.Messages;
import fr.aumgn.bukkitutils.geom.Vector;
import fr.aumgn.bukkitutils.geom.Vector2D;
import fr.aumgn.bukkitutils.util.MaterialAndData;
import fr.aumgn.bukkitutils.util.Util;

public class CommandArgs extends CommandArgsBase {

    public CommandArgs(Messages local, String[] tokens, Set<Character> allowedFlag, int min, int max) {
        super(local, tokens, allowedFlag, min, max);
    }

    public int getInteger(int index) {
        try {
            return Integer.parseInt(get(index));
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    String.format(local.notAValidNumber(), index + 1));
        }
    }

    public int getInteger(int index, int def) {
        if (hasIndex(index)) {
            return getInteger(index);
        }

        return def;
    }

    public double getDouble(int index) {
        try {
            return Double.parseDouble(get(index));
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    String.format(local.notAValidNumber(), index + 1));
        }
    }

    public double getDouble(int index, double def) {
        if (hasIndex(index)) {
            return getDouble(index);
        }

        return def;
    }

    public Vector getVector(int i) {
        String arg = get(i);
        String[] splitted = arg.split(",");

        if (splitted.length > 3) {
            throw new CommandUsageError();
        }

        double x = parseVectorComponent(splitted[0]);
        double y = 0.0;
        double z = 0.0;
        if (splitted.length > 1) {
            y = parseVectorComponent(splitted[1]);
            if (splitted.length > 2) {
                z = parseVectorComponent(splitted[2]);
            }
        }

        return new Vector(x, y, z);
    }

    public Vector getVector(int index, Vector def) {
        if (hasIndex(index)) {
            return getVector(index);
        }

        return def;
    }

    public Vector2D getVector2D(int i) {
        String arg = get(i);
        String[] splitted = arg.split(",");

        if (splitted.length > 2) {
            throw new CommandUsageError();
        }

        double x = parseVectorComponent(splitted[0]);
        double z = 0.0;
        if (splitted.length > 1) {
            z = parseVectorComponent(splitted[1]);
        }

        return new Vector2D(x,z);
    }

    public Vector2D getVector2D(int index, Vector2D def) {
        if (hasIndex(index)) {
            return getVector2D(index);
        }

        return def;
    }

    private double parseVectorComponent(String component) {
        try {
            return Double.parseDouble(component);
        } catch (NumberFormatException exc) {
            throw new CommandUsageError(
                    String.format(local.notAValidVectorComponent(), component));
        }
    }

    public World getWorld(int index) {
        World world = Bukkit.getWorld(get(index));
        if (world == null) {
            throw new NoSuchWorld(local, get(index));
        }

        return world;
    }

    public World getWorld(int index, World def) {
        if (hasIndex(index)) {
            return getWorld(index);
        }

        return def;
    }

    public Player getPlayer(int index) {
        Player player = Bukkit.getPlayer(get(index));
        if (player == null) {
            throw new NoSuchPlayer(local, get(index));
        }

        return player;
    }

    public Player getPlayer(int index, Player def) {
        if (hasIndex(index)) {
            return getPlayer(index);
        }

        return def;
    }

    public List<Player> getPlayers(int index) {
        String arg = get(index);

        if (arg.equals("*")) {
            return Arrays.asList(Bukkit.getOnlinePlayers());
        }
        return Util.matchPlayer(arg);
    }

    public OfflinePlayer getOfflinePlayer(int index) {
        return Bukkit.getOfflinePlayer(get(index));
    }

    public OfflinePlayer getOfflinePlayer(int index, OfflinePlayer def) {
        if (hasIndex(index)) {
            return getOfflinePlayer(index);
        }

        return def;
    }

    public List<OfflinePlayer> getOfflinePlayers(int index) {
        return Util.matchOfflinePlayer(get(index));
    }

    private Material getMaterial(String identifier) {
        Material material = Material.matchMaterial(identifier);
        if (material == null) {
            try {
                int id = Integer.parseInt(identifier);
                material = Material.getMaterial(id);
            } catch (NumberFormatException exc) {
            }
        }

        if (material == null) {
            throw new NoSuchMaterial(local, identifier);
        }

        return material;
    }

    public Material getMaterial(int index) {
        return getMaterial(get(index));
    }

    public Material getMaterial(int index, Material def) {
        if (hasIndex(index)) {
            return getMaterial(index);
        }

        return def;
    }

    public MaterialAndData getMaterialAndData(int index) {
        String[] splitted = get(index).split(":");
        if (splitted.length > 2) {
            throw new InvalidMaterialAndDataFormat(local, get(index));
        }

        byte data = 0;
        if (splitted.length == 2) {
            try {
                data = Byte.parseByte(splitted[1]);
            } catch (NumberFormatException exc) {
                throw new InvalidMaterialAndDataFormat(local, get(index));
            }
        }

        Material material = getMaterial(splitted[0]);
        return new MaterialAndData(material, data);
    }

    public MaterialAndData getMaterialAndData(int index, MaterialAndData def) {
        if (hasIndex(index)) {
            return getMaterialAndData(index);
        }

        return def;
    }

    public MaterialAndData getMaterialAndData(int index, Material def) {
        if (hasIndex(index)) {
            return getMaterialAndData(index);
        }

        return new MaterialAndData(def);
    }
}
