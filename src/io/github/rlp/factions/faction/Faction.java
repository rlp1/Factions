package io.github.rlp.factions.faction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rlp (18/12/2018 22:05)
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class Faction {

    private final String name;
    private final String tag;

    /**
     * This represents the members that are in the faction
     * @since 1.0
     */
    private final List<FactionMember> members;

    /**
     * This represents the faction relations that this faction has.
     * @since 1.0
     */
    private final List<FactionRelation> relations;

    /**
     * This method get the list of the online members.
     * @return the online members list.
     * @since 1.0
     */
    public List<Player> getOnlineMembers() {
        List<Player> playerList = null;

        for (final FactionMember member : this.members) {
            // @Note Get the player.
            final Player player = Bukkit.getPlayer(member.getName());

            // @Note Check if the player is different from null, then this represents that the player is online.
            if (player != null) {
                // @Note Check if the player list is equals null, then instantiate the list.
                if (playerList == null) playerList = new ArrayList<>();

                playerList.add(player);
            }
        }

        return playerList == null ? Collections.emptyList() : playerList;
    }

    /**
     * This method count the online members that are online in the server.
     * @return the count of online members
     * @since 1.0
     */
    public int countOnlineMembers() {
        int onlineMembers = 0;

        for (final FactionMember member : this.members) {
            // @Note Check if the player that is get by the bukkit is different from null.
            if (Bukkit.getPlayer(member.getName()) != null) {
                onlineMembers++;
            }
        }

        return onlineMembers;
    }

    /**
     * This method get the member name with the role informations by member name.
     *
     * @param memberName the member name.
     * @return the member name wtih role informations.
     * @since 1.0
     */
    public String getRoleName(final String memberName) {
        final FactionMember factionMember = this.getMemberByName(memberName);

        // @Note Check if the faction member is null, then return null.
        if (factionMember == null) {
            return null;
        }

        return "[" + factionMember.getRole().getSymbol() + memberName + "]";
    }

    /**
     * This method get the member name with the role informations by player.
     *
     * @param player the player.
     * @return the member name with role informations.
     * @since 1.0
     */
    public String getRoleName(final Player player) {
        return this.getRoleName(player.getName());
    }

    /**
     * This method get the member by the member name, if doesn't is a member with this name, then return null.
     *
     * @param memberName the member name.
     * @return the member instance.
     * @since 1.0
     */
    public FactionMember getMemberByName(final String memberName) {
        return this.members.stream().filter(member -> member.getName().equals(memberName)).findFirst().orElse(null);
    }

    /**
     * This method get the member by player.
     *
     * @param player the player.
     * @return the member insntace.
     * @since 1.0
     */
    public FactionMember getMemberByPlayer(final Player player) {
        return this.getMemberByName(player.getName());
    }

    /**
     * @author rlp (19/12/2018 22:09)
     * @since 1.0
     */
    @Getter
    @RequiredArgsConstructor
    public enum Role {

        LEADER("Líder", '#'),
        COMMANDER("Comandante", '*'),
        OFICIAL("Oficial", '+'),
        MEMBER("Membro", '-');

        /**
         * This represents the translated name from the role.
         * @since 1.0
         */
        private final String translatedName;

        /**
         * This represents the symbol that appears on the chat from the server and from the guild, this represents a
         * symbol to can identify the role from the player.
         * @since 1.0
         */
        private final char symbol;
    }
}
