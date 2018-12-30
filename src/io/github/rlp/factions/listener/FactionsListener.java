package io.github.rlp.factions.listener;

import io.github.rlp.factions.FactionsPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author rlp (19/12/2018 12:40)
 * @since 1.0
 */
public class FactionsListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        // @Note This method register the player into the in-game cache.
        FactionsPlugin.REGISTRY.getPlayersRegistry().registerPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        // @Note This method unregister the player from the in-game cache.
        FactionsPlugin.REGISTRY.getPlayersRegistry().unregisterPlayer(event.getPlayer());
    }
}
