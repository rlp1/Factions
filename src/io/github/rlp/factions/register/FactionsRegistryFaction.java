package io.github.rlp.factions.register;

import io.github.rlp.factions.faction.Faction;

/**
 * This class implements flexible methods to register the factions.
 * @author rlp (18/12/2018 22:04)
 * @since 1.0
 */
public class FactionsRegistryFaction extends FactionsRegistry {

    /**
     * This method load the faction from the database with the name of the faction.
     * @param name this represents the name from the faction.
     * @return the faction that is loaded from the database.
     * @since 1.0
     */
    public Faction loadFaction(final String name) {
        return null;
    }

    /**
     * This method save the faction into the database.
     * @param faction the faction that will be save.
     * @since 1.0
     */
    public void saveFaction(final Faction faction) {

    }

    /**
     * This method register the faction into in-game cache.
     * @param faction the faction informations.
     * @since 1.0
     */
    public void registerFaction(final Faction faction) {

    }

    /**
     * This method unregister the faction from the in-game cache, and this method save the faction into the database.
     * @param identifier the identifier.
     * @since 1.0
     */
    public void unregisterFaction(final String identifier) {

    }

    /**
     * This method get the faction depending from the identifier, when the identifier that represents a string has fixed
     * 3 length the identifier is set as a faction tag, when the identifier has a length bigger than the 3, then the
     * identifier is set as faction name.
     * @param identifier the faction identifier.
     * @return the faction.
     * @since 1.0
     */
    public Faction getFaction(final String identifier) {
        return null;
    }

    /**
     * This method load the all factions that are registered into the server. This method load the only factions from
     * the players that are online in the server.
     * @since 1.0
     */
    public void loadAllFactions() {

    }

    /**
     * This method save the all factions that are registered into the server.
     * @since 1.0
     */
    public void saveAllFactions() {

    }
}
