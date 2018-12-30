package io.github.rlp.factions.chunk;

import lombok.RequiredArgsConstructor;

/**
 * @author rlp (18/12/2018 22:12)
 * @since 1.0
 */
@RequiredArgsConstructor
public class FactionsChunk {

    /**
     * This represents the identifier from the chunk, this identifier represents which the faction protected this chunk.
     * And is more better use the tag from the factions to can improve the Memory Management, because can be get the
     * faction informations by two ways, by the name or by the tag, then the tag has the fixed length that is 3, instead
     * of the name from the faction.
     * @since 1.0
     */
    private final String tag;

    /**
     * This represents the coodinates X and Z from the chunk.
     */
    private final int chunkX;
    private final int chunkZ;
}
