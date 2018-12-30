package io.github.rlp.factions.entity;

import io.github.rlp.factions.register.FactionsConfiguration;
import io.github.rlp.factions.util.FactionsUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rlp (18/12/2018 21:43)
 * @since 1.0
 */
@Getter
public class FactionsPlayer {

    /**
     * This represents the faction from the player.
     * @since 1.0
     */
    private String faction = FactionsUtil.EMPTY_FACTION;

    /**
     * This represents the power and the power max from the player.
     * @since 1.0
     */
    private byte power = FactionsConfiguration.getInstance().getInitialPower();
    private byte powerMax = FactionsConfiguration.getInstance().getInitialPowerMax();

    /**
     * This represents the informations from the kills about the enemies, neutrals and the deaths of the player.
     * @since 1.0
     */
    private int killEnemies;
    private int killNeutrals;

    private int deathEnemies;
    private int deathNeutrals;

    /**
     * This represents the economy of the player, or in other words the amount of money that the player has.
     * @since 1.0
     */
    private double economy = FactionsConfiguration.getInstance().getInitialEconomy();

    /**
     * This represents the first login and the last login from the player.
     * @since 1.0
     */
    private long firstLogin = System.currentTimeMillis();
    @Setter private long lastLogin = System.currentTimeMillis();

    /**
     * This method check if the player has faction.
     * @return true if the player has faction, otherwise returns false.
     * @since 1.0
     */
    public boolean hasFaction() {
        return !faction.equals(FactionsUtil.EMPTY_FACTION);
    }

    /**
     * This method calculates the KDR of the player.
     * @return the KDR of player.
     * @since 1.0
     */
    public float getKDR() {
        final float value = (killEnemies + killNeutrals) / (float) (this.deathEnemies + this.deathNeutrals);
        return Float.isNaN(value) ? 1f : value;
    }
}
