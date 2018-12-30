package io.github.rlp.factions.register.flat;

import com.google.gson.JsonArray;
import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.chunk.FactionsChunk;
import io.github.rlp.factions.register.FactionsRegistryChunk;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author rlp (19/12/2018 14:44)
 * @since 1.0
 */
public class FactionsRegistryChunkFlat extends FactionsRegistryChunk {

    /**
     * This represents the list that store the all protected chunks.
     * @since 1.0
     */
    private final List<FactionsChunk> factionsChunk = new ArrayList<>();

    @Override
    public void loadAllChunks() {
        // @Note This represents the chunk file.
        final File chunksFile = new File(FactionsPlugin.getInstance().getDataFolder(), "chunks.json");

        try (final FileReader reader = new FileReader(chunksFile)) {
            final JsonArray chunksArray = GSON.fromJson(reader, JsonArray.class);

            chunksArray.forEach(chunksJson -> {
                // @Note Add the deserialized chunk from JSON format into object.
                this.factionsChunk.add(GSON.fromJson(chunksJson, FactionsChunk.class));
            });
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when load the faction chunks.");
            e.printStackTrace();
        }

        FactionsPlugin.getInstance().getLogger().info("A amount of \"" + this.factionsChunk.size() + "\" of chunks are loaded.");
    }

    @Override
    public void saveAllChunks() {
        // @Note This represents the chunk file.
        final File chunksFile = new File(FactionsPlugin.getInstance().getDataFolder(), "chunks.json");

        try (final FileWriter writer = new FileWriter(chunksFile)) {
            writer.write(GSON.toJson(this.factionsChunk));
        } catch (IOException e) {
            FactionsPlugin.getInstance().getLogger().severe("An error occured when save the faction chunks.");
            e.printStackTrace();
        }
    }

    @Override
    public FactionsChunk getChunk(int chunkX, int chunkZ) {
        return null;
    }

    @Override
    public void initRegistry() {
        // @Note This represents the chunk file where the chunks will be store.
        File chunksFile = new File(FactionsPlugin.getInstance().getDataFolder(), "chunks.json");

        // @Note Check if the database folder is not exists.
        if (!chunksFile.exists()) {
            try {
                chunksFile.createNewFile();
            } catch (IOException e) {
                final Logger logger = FactionsPlugin.getInstance().getLogger();
                logger.severe("");
                logger.severe("An error occured when create the chunks file from \"" + FactionsPlugin.getInstance().getName() + "\".");
                logger.severe("Please, delete the database folder relationed with this plugin, and then reload it.");
                logger.severe("The plugin will be disabled on 20 seconds.");
                logger.severe("");

                // @Note This method do that disable the plugin after 20 seconds.
                Bukkit.getScheduler().scheduleSyncDelayedTask(FactionsPlugin.getInstance(),
                        () -> Bukkit.getPluginManager().disablePlugin(FactionsPlugin.getInstance()), 20L * 20);
                e.printStackTrace();
            }
        }

        // ... Load the all faction chunks ...
        this.loadAllChunks();
    }

    @Override
    public void deInitRegistry() {
        // ... Save the all faction chunks ...
        this.saveAllChunks();
    }
}
