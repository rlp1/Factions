package io.github.rlp.factions.register.flat;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.entity.FactionsPlayer;
import io.github.rlp.factions.register.FactionsRegistryPlayer;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author rlp (18/12/2018 22:20)
 * @since 1.0
 */
public class FactionsRegistryPlayerFlat extends FactionsRegistryPlayer {

    /**
     * This represents the map, that represents the in-game cache, which storage the all players informations that
     * are online in the server.
     * @sincee 1.0
     */
    @Getter
    private final Map<Player, FactionsPlayer> factionsPlayer = new HashMap<>();

    @Override
    public FactionsPlayer loadFactionsPlayer(File playerFile) {
        try (final FileReader reader = new FileReader(playerFile)) {
            // @Note Return the FactionsPlayer object, deserialize from the JSON file format.
            return GSON.fromJson(reader, FactionsPlayer.class);
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when load the data from \"" +
                    playerFile.getName().substring(0, playerFile.getName().length() - 5) + "\".");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public FactionsPlayer loadFactionsPlayer(String playerName) {
        return this.loadFactionsPlayer(this.getPlayerFile(playerName));
    }

    @Override
    public void savePlayer(File playerFile, FactionsPlayer factionsPlayer) {
        try (final FileWriter writer = new FileWriter(playerFile)) {
            writer.write(GSON.toJson(factionsPlayer));
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when save the data from \""
                    + playerFile.getName().substring(0, playerFile.getName().length() - 5) + "\".");
            e.printStackTrace();
        }
    }

    @Override
    public void savePlayer(Player player) {
        this.savePlayer(this.getPlayerFile(player.getName()), this.factionsPlayer.get(player));
    }
    
    @Override
    public void registerPlayer(Player player) {
        final File playerFile = this.getPlayerFile(player.getName());
        final FactionsPlayer factionsPlayer;

        // @Note Check if the player file exists, then load it.
        if (playerFile.exists()) {
            factionsPlayer = this.loadFactionsPlayer(playerFile);
        }
        // @Note This represents that the player file that storage informations from the player not exists in the database,
        // then create it.
        else {
            factionsPlayer = new FactionsPlayer();

            // @Note This method save the informations from the player into the player data.
            this.savePlayer(playerFile, factionsPlayer);
        }

        // @Note Register the player and the factions player into the map.
        this.factionsPlayer.put(player, factionsPlayer);
    }

    @Override
    public void unregisterPlayer(Player player) {
        this.factionsPlayer.get(player).setLastLogin(System.currentTimeMillis());

        // @Note This method save the player into the database.
        this.savePlayer(player);

        // @Note Remove the player from the map.
        this.factionsPlayer.remove(player);
    }

    @Override
    public FactionsPlayer getFactionsPlayer(Player player) {
        return this.factionsPlayer.get(player);
    }

    @Override
    public void initRegistry() {
        // @Note This represents the players folder where the players informations will be store.
        File playersFolder = new File(FactionsPlugin.getInstance().getDataFolder(), "players");

        // @Note This method check if the players folder is not exists, and if the players folder make directory operation
        // is failed.
        if (!playersFolder.exists() && !playersFolder.mkdirs()) {
            final Logger logger = FactionsPlugin.getInstance().getLogger();
            logger.severe("");
            logger.severe("An error occured when create the players folder from \"" + FactionsPlugin.getInstance().getName() + "\".");
            logger.severe("Please, delete the database folder relationed with this plugin, and then reload it.");
            logger.severe("The plugin will be disabled on 20 seconds.");
            logger.severe("");

            // @Note This method do that disable the plugin after 20 seconds.
            Bukkit.getScheduler().scheduleSyncDelayedTask(FactionsPlugin.getInstance(),
                    () -> Bukkit.getPluginManager().disablePlugin(FactionsPlugin.getInstance()), 20L * 20);
            return;
        }

        // ... Load all players that are in server ...
        this.loadAllPlayers();
    }

    @Override
    public void deInitRegistry() {
        /// ... Save all players that are in server ...
        this.saveAllPlayers();
    }

    // # # # # # # # # # #
    // Internal Methods
    // # # # # # # # # # #

    /**
     * This method get the player file that are stored into the database folder.
     * @param playerName the player name.
     * @return the file from the player that are stored the all informations from the player.
     * @since 1.0
     */
    private File getPlayerFile(final String playerName) {
        return new File(new File(FactionsPlugin.getInstance().getDataFolder(), "players"), playerName + ".json");
    }
}
