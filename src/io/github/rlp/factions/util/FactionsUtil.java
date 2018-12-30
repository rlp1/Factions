package io.github.rlp.factions.util;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.entity.FactionsPlayer;
import io.github.rlp.factions.faction.Faction;
import io.github.rlp.factions.faction.FactionMember;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author rlp (19/12/2018 15:05)
 * @since 1.0
 */
public final class FactionsUtil {

    private FactionsUtil() { // non-instantiated.
    }

    /**
     * This represents an static-final variable that represents the name from the empty faction, this variable is used
     * to substitute the "null" from the factions players that doesn't have factions.
     * @since 1.0
     */
    public static final String EMPTY_FACTION = "_";

    /**
     * This represents the date formatter, that formats the "currentTimeMillis" into string date format.
     * @since 1.0
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy 'ás' HH:mm:ss");

    // ... getRoleName ...

    /**
     * This method get a string with the symbol and translated role name of the role.
     *
     * @param playerName the player name.
     * @param factionsPlayer the factions player, that contains the all factions player informations.
     * @return the role name.
     * @since 1.0
     */
    public static String getRoleName(final String playerName, final FactionsPlayer factionsPlayer) {
        // @Note Check if the player not has faction, then return it.
        if (!factionsPlayer.hasFaction()) {
            return "Nenhum";
        }

        // @Note Get the faction from the player.
        final Faction faction = FactionsPlugin.REGISTRY.getFactionsRegistry().getFaction(factionsPlayer.getFaction());
        final FactionMember member = faction.getMemberByName(playerName);

        return "" + member.getRole().getSymbol() + member.getRole().getTranslatedName() + "";
    }

    // ... newItemBuilder & newHeadBuilder ...

    /**
     * This method creates an instance of Item Builder.
     * @return the Item Builder instance.
     * @since 1.0
     */
    public static ItemBuilder newItemBuilder() {
        return new ItemBuilder();
    }

    /**
     * This method creates an instance of Head Builder.
     * @return the Head Builder instance.
     * @since 1.0
     */
    public static HeadBuilder newHeadBuilder() {
        return new HeadBuilder();
    }

    // ... createInventory ...

    /**
     * This method create an inventory with custom title and custom size.
     *
     * @param title the title of the inventory.
     * @param size the size of the inventory.
     * @return the inventory.
     * @since 1.0
     */
    public static Inventory createInventory(final String title, final int size) {
        return Bukkit.createInventory(null, size, title);
    }

    // ... sendMessage ...

    /**
     * This method send a bunch of messages to the player, this removes the boilerplate of reiterating the,
     * "player.sendMessage" code.
     *
     * @param player the player that send the messages.
     * @param messages the messages that will be send to the player.
     * @since 1.0
     */
    public static void sendMessage(final Player player, final String... messages) {
        for (final String message : messages) {
            player.sendMessage(message);
        }
    }

    /**
     * THis method send a bunch of messages to the player, this removes the boilerplate of reiterating the,
     * "player.sendMessage" code, and this method play a sound to the player with custom volume and pitch.
     *
     * @param player the player that send the messages.
     * @param sound the sound.
     * @param volume the volume from sound.
     * @param pitch the pitch from sound.
     * @param messages the messages that will be send.
     */
    public static void sendMessage(final Player player, final Sound sound, final float volume, final float pitch,
                                   final String... messages) {
        for (final String message : messages) {
            player.sendMessage(message);
        }
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    /**
     * This method send a bunch of messages to the player, this removes the boilerplate of reiterating the,
     * "player.sendMessage" code, and this method play a sound to the player with volume and pitch by default as 1.
     *
     * @param player the player that send the messages.
     * @param sound the sound.
     * @param messages the messages that will be send.
     */
    public static void sendMessage(final Player player, final Sound sound, final String... messages) {
        sendMessage(player, sound, 1f, 1f, messages);
    }
}
