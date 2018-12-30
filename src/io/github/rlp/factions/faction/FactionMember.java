package io.github.rlp.factions.faction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author rlp (19/12/2018 22:12)
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class FactionMember {

    /**
     * This represents the name from the faction member.
     * @since 1.0
     */
    private final String name;

    /**
     * This represents the timestamp that when the member joined in the faction.
     * @since 1.0
     */
    private final long joinedAt;

    /**
     * This represents the role from the faction member, the default role from the faction member is defined to "MEMBER".
     * @since 1.0
     */
    @Setter
    private Faction.Role role = Faction.Role.MEMBER;
}
