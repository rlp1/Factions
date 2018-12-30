package io.github.rlp.factions;

import io.github.rlp.factions.register.flat.FactionsRegistryFlat;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author rlp (18/12/2018 21:47)
 * @since 0.1
 */
public class FactionsPlugin extends JavaPlugin  {

    /**
     * This represents the main registry handler. This represents the factions registry using the flat-file database,
     * then the registries from the (players, factions & chunks) also is set into flat-file database.
     * @since 1.0
     */
    public static final FactionsRegistryFlat REGISTRY = new FactionsRegistryFlat();

    /**
     * This represents the factions plugin instance, this represents a static-variable that give flexibility access
     * to other classes to can get the informations from this class.
     * @since 1.0
     */
    @Getter
    public static FactionsPlugin instance;

    @Override
    public void onEnable() {
        // ... Initialize Instance ...
        instance = this;

        // ... Initialize the all registry (players, factions & chunks) ...
        REGISTRY.initRegistry();
    }

    @Override
    public void onDisable() {
        // ... De-initialize the all registry (players, factions & chunks) ...
        REGISTRY.deInitRegistry();
    }
}
