package io.github.rlp.factions.faction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author rlp (19/12/2018 22:16)
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class FactionRelation {

    /**
     * This represents the faction name that the faction has relation.
     * @since 1.0
     */
    private final String factionName;

    /**
     * This represents the type of relation that the faction has.
     * @since 1.0
     */
    @Setter
    private Relation relation;

    /**
     * @author rlp (19/12/2018 22:17)
     * @since 1.0
     */
    @RequiredArgsConstructor
    public enum Relation {

        ALLY("Aliado"),
        ENEMY("Inimigo");

        /**
         * This represents the translated name from teh relation.
         * @since 1.0
         */
        private final String translatedName;
    }
}
