package io.github.rlp.factions.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * @author rlp
 * @since 1.0
 */
public class ItemBuilder implements Builder<ItemStack> {

    protected ItemStack item;
    protected ItemMeta meta;

    protected ItemBuilder() {
        this.item = new ItemStack(Material.AIR, 1);
    }

    public ItemBuilder setMateral(final Material material) {
        this.item.setType(material);
        return this;
    }

    public ItemBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(final byte data) {
        this.item.getData().setData(data);
        return this;
    }

    public ItemBuilder setName(final String name) {
        this.checkItemMeta();
        this.meta.setDisplayName(name);
        this.updateItemMeta();
        return this;
    }

    public ItemBuilder setLore(final String... lore) {
        this.checkItemMeta();
        this.meta.setLore(Arrays.asList(lore));
        this.updateItemMeta();
        return this;
    }

    public ItemBuilder addEnchant(final Enchantment enchantment, final int level) {
        this.checkItemMeta();
        this.meta.addEnchant(enchantment, level, true);
        this.updateItemMeta();
        return this;
    }

    public ItemBuilder addFlags(final ItemFlag... flags) {
        this.checkItemMeta();
        this.meta.addItemFlags(flags);
        this.updateItemMeta();
        return this;
    }

    @Override
    public ItemStack build() {
        return this.item;
    }

    // ... Internal Methods ...

    /**
     * This method check if the item meta is null, then get the item meta by the item stack.
     * @since 1.0
     */
    protected void checkItemMeta() {
        if (this.meta == null) {
            this.meta = this.item.getItemMeta();
        }
    }

    /**
     * This method update the item meta.
     * @since 1.0
     */
    protected void updateItemMeta() {
        this.item.setItemMeta(this.meta);
    }
}
