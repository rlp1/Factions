package io.github.rlp.factions.register;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This represents the configuration from the plugin, this represents a file configuration that not depending from
 * the generic database type.
 *
 * @author rlp
 * @since 1.0
 */
// @Getter, a something with this annotation in the class not give the get() methods to the variables.
public class FactionsConfiguration {

    /**
     * This represents the factions configuration, that is a static variable, that give more flexibility to other
     * classes access the configuration.
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private static FactionsConfiguration instance;

    /**
     * This represents the initial power and the initial power max that the player has when join in the server.
     * @since 1.0
     */
    @Getter private byte initialPower = 5;
    @Getter private byte initialPowerMax = 5;

    /**
     * This represents the power that the player loss when the player death, but, when a player death for a player the
     * power that the player loss is 2, that represents the 100% of the configuration power. But, when the player death
     * for the environment of the game the player only loss 50% of the power configuration.
     */
    @Getter private byte onDeathPower = 2;
    @Getter private byte onKillPower = 1;

    /**
     * This represents the initial economy that the player has when join in the server.
     * @since 1.0
     */
    @Getter private double initialEconomy = 50.0D;
}
