package io.github.rlp.factions.register.flat;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.register.FactionsRegistry;

import io.github.rlp.factions.register.FactionsRegistryCommand;
import io.github.rlp.factions.register.FactionsRegistryListener;
import lombok.Getter;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author rlp (18/12/2018 22:19)
 * @since 1.0
 */
public class FactionsRegistryFlat extends FactionsRegistry {

    /**
     * This represents the registry from the players, using the flat-file database.
     * @since 1.0
     */
    @Getter private final FactionsRegistryPlayerFlat playersRegistry = new FactionsRegistryPlayerFlat();

    /**
     * This represents the registry from the factions, using the flat-file database.
     * @since 1.0
     */
    @Getter private final FactionsRegistryFactionFlat factionsRegistry = new FactionsRegistryFactionFlat();

    /**
     * This represents the registry from the chunks, using the flat-file databse.
     * @since 1.0
     */
    @Getter private final FactionsRegistryChunkFlat chunksRegistry = new FactionsRegistryChunkFlat();

    /**
     * This represents the registry from the listeners.
     * @since 1.0
     */
    @Getter private final FactionsRegistryListener listenerRegistry = new FactionsRegistryListener();

    /**
     * This represents thee registry from the commands.
     * @since 1.0
     */
    @Getter private final FactionsRegistryCommand commandRegistry = new FactionsRegistryCommand();

    @Override
    public void initRegistry() {
        super.initRegistry();

        // ... Initialize the main registry ...
        final File databaseFolder = FactionsPlugin.getInstance().getDataFolder();

        // @Note Check if the database folder is not exists, and if the database folder operation make directory is
        // failed.
        if (!databaseFolder.exists() && !databaseFolder.mkdirs()) {
            final Logger logger = FactionsPlugin.getInstance().getLogger();
            logger.severe("");
            logger.severe("An error occured when create the database folder from \"" + FactionsPlugin.getInstance().getName() + "\".");
            logger.severe("Please, delete the database folder relationed with this plugin, and then reload it.");
            logger.severe("The plugin will be disabled on 20 seconds.");
            logger.severe("");

            // @Note This method do that disable the plugin after 20 seconds.
            Bukkit.getScheduler().scheduleSyncDelayedTask(FactionsPlugin.getInstance(),
                    () -> Bukkit.getPluginManager().disablePlugin(FactionsPlugin.getInstance()), 20L * 20);
            return;
        }

        // ... Initialize the registries ...
        this.playersRegistry.initRegistry();
        this.factionsRegistry.initRegistry();
        this.chunksRegistry.initRegistry();
        this.listenerRegistry.initRegistry();
        this.commandRegistry.initRegistry();
    }

    @Override
    public void deInitRegistry() {
        /// ... De-initialize the registries ...
        this.playersRegistry.deInitRegistry();
        this.factionsRegistry.deInitRegistry();
        this.chunksRegistry.deInitRegistry();
    }
}
