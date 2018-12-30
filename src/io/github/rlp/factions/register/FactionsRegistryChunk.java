package io.github.rlp.factions.register;

import io.github.rlp.factions.chunk.FactionsChunk;
import org.bukkit.Location;

/**
 * This class implements flexible methods to register the chunk.
 * @author rlp (18/12/2018 22:04)
 * @since 1.0
 */
public class FactionsRegistryChunk extends FactionsRegistry {

    /**
     * This method load the all chunks that are registered in the server.
     * @since 1.0
     */
    public void loadAllChunks() {

    }

    /**
     * This method save the all chunks that are registered in the server.
     * @since 1.0
     */
    public void saveAllChunks() {

    }

    /**
     * This method get the chunk that is positioned on the chunk X and chunk Z.
     * @param chunkX the chunk X coordinate.
     * @param chunkZ the chunk Z coordinate.
     * @return the factions chunk.
     * @since 1.0
     */
    public FactionsChunk getChunk(final int chunkX, final int chunkZ) {
        return null;
    }

    /**
     * This method get the chunk that is positioned on the location.
     * @param location the location.
     * @return the faction chunk.
     * @since 1.0
     */
    public FactionsChunk getChunk(final Location location) {
        return this.getChunk(location.getChunk().getX(), location.getChunk().getZ());
    }
}
