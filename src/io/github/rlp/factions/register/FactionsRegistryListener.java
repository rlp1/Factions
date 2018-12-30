package io.github.rlp.factions.register;

import com.google.common.base.Preconditions;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.listener.FactionsListener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * @author rlp
 * @since 1.0
 */
public final class FactionsRegistryListener extends FactionsRegistry {

    @Override
    public void initRegistry() {
        this.registerListener(new FactionsListener());
    }

    /**
     * This method register the events that contains in the listener into the game.
     *
     * @param listener the listener, that will be registered.
     *
     * @since 1.0
     */
    public void registerListener(final Listener listener) {
        Preconditions.checkNotNull(listener, "listener");

        // @Note Register the listener & events.
        Bukkit.getPluginManager().registerEvents(listener, FactionsPlugin.getInstance());
    }

    /**
     * This method unregister the events that contains in the listener from the game.
     *
     * @param listener the listener, that will be unregistered.
     */
    public void unregisterListener(final Listener listener) {
        Preconditions.checkNotNull(listener, "listener");

        // @Note Unregister the listener & events.
        HandlerList.unregisterAll(listener);
    }
}
