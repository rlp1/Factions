package io.github.rlp.factions.gui;

import org.bukkit.entity.Player;

/**
 * @author rlp
 * @since 1.0
 */
@FunctionalInterface
public interface GUIButton {

    /**
     * This represents the function of the button.
     * @param player the player that clicked on the button.
     * @since 1.0
     */
    void run(final Player player);
}
