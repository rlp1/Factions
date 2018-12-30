package io.github.rlp.factions.gui;

import com.google.common.base.Preconditions;

import io.github.rlp.factions.FactionsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rlp
 * @since 1.0
 */
public abstract class GUI implements Listener {

    /**
     * This represents the inventory.
     * @since 1.0
     */
    protected Inventory inventory;

    /**
     * This represents the player that will handle the inventory.
     * @since 1.0
     */
    protected Player player;

    /**
     * This represents the map that storage the all buttons that are registered in the GUI. The buttons map functions
     * on the lazy initialization.
     * @since 1.0
     */
    private Map<Integer, GUIButton> buttons;

    public GUI() {
    }

    public GUI(final Inventory inventory, final Player player) {
        this.inventory = inventory;
        this.player = player;

        // @Note This method build the page.
        this.buildPage();
    }

    /**
     * This method build the page.
     * @since 1.0
     */
    public abstract void buildPage();

    /**
     * This method set that the slot is non clicked. This method add a null button into the current slot.
     * @param slot the slot index.
     * @since 1.0
     */
    public void setNonClickable(final int slot) {
        if (this.buttons == null) this.buttons = new HashMap<>();

        // @Note Put in the slot a null button function.
        this.buttons.put(slot, null);
    }

    /**
     * This method add a button into the slot and your functionality.
     *
     * @param slot the slot of button.
     * @param button the button function.
     * @since 1.0
     */
    public void addButton(final int slot, final GUIButton button) {
        Preconditions.checkArgument(slot >= 0 && slot < this.inventory.getSize(), "slot exceeds " +
                "the inventory size limit that is (" + this.inventory.getSize() + ")");
        Preconditions.checkNotNull(button, "button");

        // @Note Check if the buttons map is null, then initialize it.
        if (this.buttons == null) this.buttons = new HashMap<>();

        // @Note Put the slot with button.
        this.buttons.put(slot, button);
    }

    /**
     * This method set the item stack into the slot, and in this slot is add a button.
     *
     * @param itemStack the item stack.
     * @param slot the slot of button and of item.
     * @param button the button function.
     * @since 1.0
     */
    public void addButtonWithItem(final ItemStack itemStack, final int slot, final GUIButton button) {
        this.inventory.setItem(slot, itemStack);
        this.addButton(slot, button);
    }

    // ... Static Methods ...

    /**
     * This method register the GUI listener, and open the GUI to the player.
     *
     * @param gui the GUI.
     * @since 1.0
     */
    public static void openGUI(final GUI gui) {
        // @Note Register the GUI listener.
        FactionsPlugin.REGISTRY.getListenerRegistry().registerListener(gui);

        // @Note Open the inventory from the GUI to the player.
        gui.player.openInventory(gui.inventory);
    }

    // ... Internal Events ...

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        // @Note Check if the player that clicked in the current inventory is different from the player that handle
        // this inventory, then return it.
        if (this.player != event.getWhoClicked());

        // @Note Check if the current clicked inventory is not equals with the inventory from this GUI, then return it.
        if (!ChatColor.stripColor(event.getClickedInventory().getName()).equals(ChatColor
                .stripColor(this.inventory.getName()))) return;

        // @Note Check if the buttons map is null, then return it. This represents that the GUI doesn't have buttons.
        if (this.buttons == null) return;

        // @Note Check if has a button in the slot that the player has clicked.
        if (!this.buttons.containsKey(event.getSlot())) return;

        // @Note This method run the function of the button.
        final GUIButton button = this.buttons.get(event.getSlot());

        // @Note Check if the button is null, then return it.
        if (button == null) {
            event.setCancelled(true);
            return;
        }

        button.run(this.player);

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        // @Note Check if the player that close the current inventory is different from the player that handle this
        // inventory, then return it.
        if (this.player != event.getPlayer()) return;

        // @Note Unregister the listener.
        FactionsPlugin.REGISTRY.getListenerRegistry().unregisterListener(this);
    }
}
