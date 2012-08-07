package fr.aumgn.bukkitutils.mockbukkit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.avaje.ebean.config.ServerConfig;

public class MockServer implements Server {

    private Map<String, Player> players;
    private Map<String, World> worlds;

    public void init() {
        players = new HashMap<String, Player>();
        worlds = new HashMap<String, World>();
    }

    public Map<String, Player> getPlayersMap() {
        return players;
    }

    public Map<String, World> getWorldsMap() {
        return worlds;
    }

    @Override
    public String getName() {
        return "MockBukkit";
    }

    @Override
    public String getVersion() {
        return "0.0.0";
    }

    @Override
    public String getBukkitVersion() {
        return "0.0-R0";
    }

    @Override
    public Player[] getOnlinePlayers() {
        Collection<Player> onlinePlayers = players.values();
        return onlinePlayers.toArray(new Player[players.size()]);
    }

    @Override
    public int getMaxPlayers() {
        return anyInt();
    }

    @Override
    public int getPort() {
        return anyInt();
    }

    @Override
    public int getViewDistance() {
        return anyInt();
    }

    @Override
    public String getIp() {
        return anyString();
    }

    @Override
    public String getServerName() {
        return anyString();
    }

    @Override
    public String getServerId() {
        return anyString();
    }

    @Override
    public String getWorldType() {
        return anyString();
    }

    @Override
    public boolean getGenerateStructures() {
        return anyBoolean();
    }

    @Override
    public boolean getAllowEnd() {
        return anyBoolean();
    }

    @Override
    public boolean getAllowNether() {
        return anyBoolean();
    }

    @Override
    public boolean hasWhitelist() {
        return anyBoolean();
    }

    @Override
    public void setWhitelist(boolean value) {
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return anySetOf(OfflinePlayer.class);
    }

    @Override
    public void reloadWhitelist() {
    }

    @Override
    public int broadcastMessage(String message) {
        return anyInt();
    }

    @Override
    public String getUpdateFolder() {
        return anyString();
    }

    @Override
    public File getUpdateFolderFile() {
        throw new NotImplementedException();
    }

    @Override
    public long getConnectionThrottle() {
        return anyLong();
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return anyInt();
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return anyInt();
    }

    @Override
    public Player getPlayer(String rawName) {
        Player found = null;
        String name = rawName.toLowerCase();
        int delta = Integer.MAX_VALUE;
        for (Player player : players.values()) {
            if (player.getName().toLowerCase().startsWith(name)) {
                int curDelta = player.getName().length() - name.length();
                if (curDelta < delta) {
                    found = player;
                    delta = curDelta;
                }
                if (curDelta == 0) break;
            }
        }
        return found;
    }

    @Override
    public Player getPlayerExact(String name) {
        return players.get(name.toLowerCase());
    }

    @Override
    public List<Player> matchPlayer(String rawPattern) {
        List<Player> matchedPlayers = new ArrayList<Player>();
        String pattern = rawPattern.toLowerCase(Locale.ENGLISH);

        for (Player player : players.values()) {
            String playerName = player.getName()
                    .toLowerCase(Locale.ENGLISH);
            if (pattern.equals(playerName)) {
                return Collections.<Player>singletonList(player);
            } else if (playerName.contains(pattern)) {
                matchedPlayers.add(player);
            }
        }

        return matchedPlayers;
    }

    @Override
    public PluginManager getPluginManager() {
        throw new NotImplementedException();
    }

    @Override
    public BukkitScheduler getScheduler() {
        throw new NotImplementedException();
    }

    @Override
    public ServicesManager getServicesManager() {
        throw new NotImplementedException();
    }

    @Override
    public List<World> getWorlds() {
        return new ArrayList<World>(worlds.values());
    }

    public World createWorld(WorldCreator creator) {
        throw new NotImplementedException();
    }

    @Override
    public boolean unloadWorld(String name, boolean save) {
        throw new NotImplementedException();
    }

    @Override
    public boolean unloadWorld(World world, boolean save) {
        throw new NotImplementedException();
    }

    @Override
    public World getWorld(String name) {
        return worlds.get(name);
    }

    public World getWorld(UUID uid) {
        throw new NotImplementedException();
    }

    @Override
    public MapView getMap(short id) {
        throw new NotImplementedException();
    }

    @Override
    public MapView createMap(World world) {
        throw new NotImplementedException();
    }

    @Override
    public void reload() {
        throw new NotImplementedException();
    }

    @Override
    public Logger getLogger() {
        return mock(Logger.class);
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        throw new NotImplementedException();
    }

    @Override
    public void savePlayers() {
        throw new NotImplementedException();
    }

    public boolean dispatchCommand(CommandSender sender, String commandLine)
            throws CommandException {
        throw new NotImplementedException();
    }

    @Override
    public void configureDbConfig(ServerConfig config) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        throw new NotImplementedException();
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        throw new NotImplementedException();
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        throw new NotImplementedException();
    }

    @Override
    public void clearRecipes() {
        throw new NotImplementedException();
    }

    @Override
    public void resetRecipes() {
        throw new NotImplementedException();
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        return anyMapOf(String.class, String[].class);
    }

    @Override
    public int getSpawnRadius() {
        return anyInt();
    }

    @Override
    public void setSpawnRadius(int value) {
    }

    @Override
    public boolean getOnlineMode() {
        return anyBoolean();
    }

    @Override
    public boolean getAllowFlight() {
        return anyBoolean();
    }

    @Override
    public boolean useExactLoginLocation() {
        return anyBoolean();
    }

    @Override
    public void shutdown() {
    }

    @Override
    public int broadcast(String message, String permission) {
        throw new NotImplementedException();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        String lname = name.toLowerCase(Locale.ENGLISH);
        OfflinePlayer player = getPlayerExact(name);
        if (player == null) {
            player = mock(OfflinePlayer.class);
            when(player.getName()).thenReturn(lname);
        }

        return player;
    }

    @Override
    public Set<String> getIPBans() {
        throw new NotImplementedException();
    }

    @Override
    public void banIP(String address) {
        throw new NotImplementedException();
    }

    @Override
    public void unbanIP(String address) {
        throw new NotImplementedException();
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        throw new NotImplementedException();
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        throw new NotImplementedException();
    }

    @Override
    public GameMode getDefaultGameMode() {
        return any(GameMode.class);
    }

    @Override
    public void setDefaultGameMode(GameMode mode) {
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        throw new NotImplementedException();
    }

    @Override
    public File getWorldContainer() {
        throw new NotImplementedException();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        throw new NotImplementedException();
    }

    @Override
    public Messenger getMessenger() {
        throw new NotImplementedException();
    }

    @Override
    public HelpMap getHelpMap() {
        throw new NotImplementedException();
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        throw new NotImplementedException();
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size) {
        throw new NotImplementedException();
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) {
        throw new NotImplementedException();
    }

    public int getMonsterSpawnLimit() {
        return anyInt();
    }

    public int getAnimalSpawnLimit() {
        return anyInt();
    }

    public int getWaterAnimalSpawnLimit() {
        return anyInt();
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw new NotImplementedException();
    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        throw new NotImplementedException();
    }

    @Override
    public String getMotd() {
        throw new NotImplementedException();
    }

    @Override
    public WarningState getWarningState() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isPrimaryThread() {
        return anyBoolean();
    }
}
