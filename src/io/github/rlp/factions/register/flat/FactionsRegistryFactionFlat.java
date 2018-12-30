package io.github.rlp.factions.register.flat;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.entity.FactionsPlayer;
import io.github.rlp.factions.faction.Faction;
import io.github.rlp.factions.register.FactionsRegistryFaction;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author rlp (19/12/2018 11:03)
 * @since 1.0
 */
public class FactionsRegistryFactionFlat extends FactionsRegistryFaction {

    /**
     * This represents the list that storage the registered factions in in-game cache.
     * @since 1.0
     */
    @Getter
    private final List<Faction> factions = new ArrayList<>();

    @Override
    public Faction loadFaction(String name) {
        // @Note This represents the faction file.
        final File factionFile = this.getFactionFile(name);

        try (final FileReader reader = new FileReader(factionFile)) {
            // @Note This represents the Faction deserialize from JSON format by GSON.
            return GSON.fromJson(reader, Faction.class);
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when load the faction \"" + name + "\".");
            e.printStackTrace();
        }

        // @Note This method returns null, if the operation of load the faction was failed.
        return null;
    }

    @Override
    public void saveFaction(Faction faction) {
        // @Note This represents the faction file.
        final File factionFile = this.getFactionFile(faction.getName());

        try (final FileWriter writer = new FileWriter(factionFile)) {
            writer.write(GSON.toJson(faction));
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when save the faction \"" + faction.getName() + "\".");
            e.printStackTrace();
        }
    }

    @Override
    public void registerFaction(Faction faction) {
        // @Note Check if the faction is already registered into the list.
        if (this.getFaction(faction.getName()) != null) return;

        // @Note Add the factions into the list.
        this.factions.add(faction);
    }

    @Override
    public void unregisterFaction(String identifier) {
        // @Note Remove the faction from the list.
        this.factions.remove(this.getFaction(identifier));
    }

    @Override
    public void loadAllFactions() {
        // @Note List the all players that are online in the server.
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final FactionsPlayer factionsPlayer = FactionsPlugin.REGISTRY.getPlayersRegistry().getFactionsPlayer(player);

            // @Note Check if the player has faction.
            if (factionsPlayer.hasFaction()) {
                // @Note This method register the faction into the list. This method already do the check if the faction
                // is already registered.
                this.registerFaction(this.loadFaction(factionsPlayer.getFaction()));
            }
        }

        FactionsPlugin.getInstance().getLogger().info("A amount of \"" + this.factions.size() + "\" of factions are loaded.");
    }

    @Override
    public void saveAllFactions() {
        // @Note This method save the all factions that are online in the server.
        // this.factions.forEach(this::saveFaction);

        // @Note Is preferred the use of the for-loop instead of forEach, because of the performance that the primitive
        // keywords give to the application.
        for (final Faction faction : this.factions) {
            this.saveFaction(faction);
        }
    }

    @Override
    public Faction getFaction(String identifier) {
        // @Note Check if the identifier length is equals of 3, then the identifier is set as faction tag.
        if (identifier.length() == 3) {
            for (final Faction faction : this.factions) {
                if (faction.getTag().equalsIgnoreCase(identifier)) {
                    return faction;
                }
            }
        }

        // @Note Check if the identifier length is bigger than 3, then the identifier is set as faction name.
        if (identifier.length() > 3) {
            for (final Faction faction : this.factions) {
                if (faction.getName().equalsIgnoreCase(identifier)) {
                    return faction;
                }
            }
        }

        return null;
    }

    @Override
    public void initRegistry() {
        // @Note This represents the factions folder where teh factions will be store.
        final File factionsFolder = new File(FactionsPlugin.getInstance().getDataFolder(), "factions");

        // @Note Check if the database folder is not exists, and if the database folder operation make directory is
        // failed.
        if (!factionsFolder.exists() && !factionsFolder.mkdirs()) {
            final Logger logger = FactionsPlugin.getInstance().getLogger();
            logger.severe("");
            logger.severe("An error occured when create the factions folder from \"" + FactionsPlugin.getInstance().getName() + "\".");
            logger.severe("Please, delete the database folder relationed with this plugin, and then reload it.");
            logger.severe("The plugin will be disabled on 20 seconds.");
            logger.severe("");

            // @Note This method do that disable the plugin after 20 seconds.
            Bukkit.getScheduler().scheduleSyncDelayedTask(FactionsPlugin.getInstance(),
                    () -> Bukkit.getPluginManager().disablePlugin(FactionsPlugin.getInstance()), 20L * 20);
            return;
        }

        // ... Load the all factions ...
        this.loadAllFactions();
    }

    @Override
    public void deInitRegistry() {
        // ... Save all factions ...
        this.saveAllFactions();
    }

    // # # # # # # # # # #
    // Internal Methods
    // # # # # # # # # # #

    /**
     * This method get the faction file that store the all informations from the faction.
     * @param factionName the faction name.
     * @return the faction file that store the all informations from the faction.
     * @since 1.0
     */
    private File getFactionFile(final String factionName) {
        return new File(new File(FactionsPlugin.getInstance().getDataFolder(), "factions"), factionName + ".json");
    }
}
