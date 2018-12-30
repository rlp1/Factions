package io.github.rlp.factions.register;

import io.github.rlp.factions.entity.FactionsPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * This class implements flexible methods to register the players.
 * @author rlp (18/12/2018 22:04)
 * @since 1.0
 */
public class FactionsRegistryPlayer extends FactionsRegistry {

    /**
     * This method load the informations from a factions player, by the database file.
     * @param playerFile the player file, that contains the all informations from the factions player.
     * @return the factions player instance.
     * @since 1.0
     */
    public FactionsPlayer loadFactionsPlayer(final File playerFile) {
        return null;
    }

    /**
     * This method load the informations from a offline player, the different from this method is that the informations
     * from the player is loaded by player name.
     * @param playerName the player name from the player.
     * @return FactionsPlayer instance with the informations that are loaded from the database.
     * @since 1.0
     */
    public FactionsPlayer loadFactionsPlayer(final String playerName) {
        return null;
    }

    /**
     * This method load the informations from the player that are registered in the generic database into in-game cache.
     * @param player the player.
     * @return FactionsPlayer instance with the informations that are loaded from the database.
     * @since 1.0
     */
    public FactionsPlayer loadFactionsPlayer(final Player player) {
        return this.loadFactionsPlayer(player.getName());
    }

    /**
     * This method get the factions players that are registered by the player that represents the key in the key-value
     * storage.
     * @param player the player.
     * @return the factions player.
     * @since 1.0
     */
    public FactionsPlayer getFactionsPlayer(final Player player) {
        return null;
    }

    /**
     * This method get the factions player by the player name, if the player is online in the server, then this method
     * only get the informations from factions player in the in-game cache, otherwise load the player from database.
     *
     * @param playerName the player name.
     * @return the factions player.
     * @since 1.0
     */
    public FactionsPlayer getFactionsPlayer(final String playerName) {
        final Player player = Bukkit.getPlayer(playerName);

        // @Note If the player instance is different from null, then this represents that the player is online in the
        // server.
        if (player != null) {
            return this.getFactionsPlayer(player);
        }
        // @Note This represents that the player is offline, then load the factions player informations from the
        // database.
        else {
            return this.loadFactionsPlayer(playerName);
        }
    }

    /**
     * This method save the informations that is stored in the factions player instance in in-game cache, and write it
     * into the player file to save this informations into the database.
     * @param playerFile the player file.
     * @param factionsPlayer the factions player instance.
     * @since 1.0
     */
    public void savePlayer(final File playerFile, final FactionsPlayer factionsPlayer) {

    }

    /**
     * This method save the informations of the player that are registered in in-game cache and save into the generic
     * database.
     * @param player the player.
     * @since 1.0
     */
    public void savePlayer(final Player player) {

    }

    /**
     * This method register the player with the factions player into the map, by the key-value model, then the player
     * represents the key and the value represents the faction player.
     * @param player the player that represents the key to access the factions player information.
     * @since 1.0
     */
    public void registerPlayer(final Player player) {

    }

    /**
     * This method unregister the player from the map, the value from this key that represents the factions player also
     * is deleted from the map.
     * @param player the player.
     * @since 1.0
     */
    public void unregisterPlayer(final Player player) {

    }

    /**
     * This method load the all players that are online in the server. The concept of this method is used when the server
     * takes re-enable and when the generic database is a file database, because with MySQL databases the correctly re-load
     * from the players is the restart from the server.
     * @since 1.0
     */
    public void loadAllPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::registerPlayer);
    }

    /**
     * This method save the all players that are online in the server.
     * @since 1.0
     */
    public void saveAllPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::unregisterPlayer);
    }
}
