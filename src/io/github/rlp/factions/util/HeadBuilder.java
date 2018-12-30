package io.github.rlp.factions.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * @author rlp
 * @since 1.0
 */
public class HeadBuilder extends ItemBuilder {

    protected HeadBuilder() {
        this.item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
    }

    /**
     * This method is disabled because the unique type of material to create a head is the "SKULL_ITEM".
     */
    @Override
    public ItemBuilder setMateral(final Material material) {
        throw new UnsupportedOperationException("Unsupported method.");
    }

    /**
     * This method set a texture to the head without need the player name.
     * @param textureValue the texture value.
     * @since 1.0
     */
    public HeadBuilder setTexture(final String textureValue) {
        this.checkItemMeta();
        final SkullMeta skullMeta = (SkullMeta) this.meta;

        try {
            final Class<?> craftMetaSkullClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaSkull");
            final Field gameProfileField = craftMetaSkullClass.getDeclaredField("profile");
            gameProfileField.setAccessible(true);

            final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
            final Property textureProperty = new Property("textures", textureValue);

            gameProfile.getProperties().put("textures", textureProperty);

            // @Note Updatee the GameProfile field into the Skull Meta.
            gameProfileField.set(skullMeta, gameProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // @Note Update the item meta.
        this.updateItemMeta();
        return this;
    }

    public HeadBuilder setOwner(final String owner) {
        this.checkItemMeta();
        ((SkullMeta) this.meta).setOwner(owner);
        this.updateItemMeta();
        return this;
    }
}
