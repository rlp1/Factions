package io.github.rlp.factions.gui.custom;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.entity.FactionsPlayer;
import io.github.rlp.factions.gui.GUI;

import io.github.rlp.factions.util.FactionsUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

/**
 * @author rlp
 * @since 1.0
 */
public class PlayerInfoGUI extends GUI {

    /**
     * This represents the name from the player that has your all factions informations shown in the GUI.
     * @since 1.0
     */
    private final String whoName;

    public PlayerInfoGUI(final Player player, final String whoName) {
        this.whoName = whoName;
        this.inventory = FactionsUtil.createInventory(this.buildInventoryTitle(whoName), 27);
        this.player = player;

        this.buildPage();
    }

    @Override
    public void buildPage() {
        final FactionsPlayer factionsPlayer = FactionsPlugin.REGISTRY.getPlayersRegistry().getFactionsPlayer(this.whoName);

        final ItemStack whoHead = FactionsUtil.newHeadBuilder()
                .setOwner(this.whoName)
                .setName("§7" + this.whoName)
                .setLore(this.buildPlayerInfo(this.whoName))
                .build();

        // @Note Check if the player has faction, then set head from player and your faction button in indexes 15, 16.
        if (factionsPlayer.hasFaction()) {
            this.inventory.setItem(12, whoHead);
            this.inventory.setItem(14, FactionsUtil.newHeadBuilder()
                    .setOwner("MHF_Info")
                    // @Note Add the guild info.
                    .build()
            );

            this.setNonClickable(12);
            this.setNonClickable(14);
        }
        // @Note If the player doesn't have a faction, then set the who head in the index 16.
        else {
            this.inventory.setItem(13, whoHead);
            this.setNonClickable(13);
        }
    }

    /**
     * This method build the inventory title depending from the informations of the "who" player, that represents the
     * player that has your informations shown in player info.
     *
     * @param whoName the who player name.
     * @return the inventory title.
     * @since 1.0
     */
    private String buildInventoryTitle(final String whoName) {
        final FactionsPlayer factionsPlayer = FactionsPlugin.REGISTRY.getPlayersRegistry().getFactionsPlayer(whoName);

        // @Note Check if the player has faction, then return the member name with role informations.
        if (factionsPlayer.hasFaction()) {
            return FactionsPlugin.REGISTRY.getFactionsRegistry().getFaction(factionsPlayer.getFaction())
                    .getRoleName(whoName);
        }

        return whoName;
    }

    /**
     * This method build the player informations, that will represent the lore, that will be set on the head that
     * represents the player in the GUI.
     *
     * @param playerName the player name.
     * @return the string array with the informations, that represents the lore.
     * @since 1.0
     */
    private String[] buildPlayerInfo(final String playerName) {
        final FactionsPlayer factionsPlayer = FactionsPlugin.REGISTRY.getPlayersRegistry().getFactionsPlayer(playerName);
        final float kdr = factionsPlayer.getKDR();

        return new String[] {
                "§ePoder: §b" + factionsPlayer.getPower() + "/" + factionsPlayer.getPowerMax(),
                "§eMoedas: §b" + NumberFormat.getInstance().format(factionsPlayer.getEconomy()),
                "§eCargo: §b" + FactionsUtil.getRoleName(playerName, factionsPlayer),
                "§eKDR: §b" + (kdr < 1.0f ? "§c" : "§a") + kdr,
                "§eAbates: §b" + (factionsPlayer.getKillEnemies() + factionsPlayer.getKillNeutrals()) + " §8[Inimigo: §7" +
                        factionsPlayer.getKillEnemies() + " §8Neutro: §7" + factionsPlayer.getKillNeutrals() + "§8]",
                "§eMortes: §b" + (factionsPlayer.getDeathEnemies() + factionsPlayer.getDeathNeutrals()) + " §8[Inimigo: §7"
                        + factionsPlayer.getDeathEnemies() + " §8Neutro: §7" + factionsPlayer.getDeathNeutrals() + "§8]",
                "§eStatus: §b" + (Bukkit.getPlayer(playerName) != null ? "§aOnline" : "§cOffline"),
                "§eÚltimo login: §b" + FactionsUtil.DATE_FORMAT.format(factionsPlayer.getLastLogin())
        };
    }
}
