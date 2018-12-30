package io.github.rlp.factions.register;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.rlp.factions.FactionsPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This represents the interface from the Factions Regisrtry.
 * This class is created with the concept to create a more flexibility about registries and databases.
 *
 * @author rlp (18/12/2018 21:42)
 * @since 1.0
 */
public class FactionsRegistry {

    /**
     * This represents the GSON, this serialize the objects into JSON Format, and deserialize it into objects.
     * The GSON variable not depending from the registry type, because in both of the types this variable should be used.
     * @since 1.0
     */
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * This method initialize the main registries that the Factions Plugin must be do.
     * @since 1.0
     */
    public void initRegistry() {
        final File configurationFile = new File(FactionsPlugin.getInstance().getDataFolder(), "config.json");
        final FactionsConfiguration configuration;

        if (configurationFile.exists()) {
            try (final FileReader reader = new FileReader(configurationFile)) {
                configuration = GSON.fromJson(reader, FactionsConfiguration.class);
            } catch (IOException e) {
                FactionsPlugin.getInstance().getLogger().severe("An error occured when load the file configuration.");
                e.printStackTrace();
                return;
            }
        } else {
            configuration = new FactionsConfiguration();

            try (final FileWriter writer = new FileWriter(configurationFile)) {
                writer.write(GSON.toJson(configuration));
            } catch (IOException e) {
                FactionsPlugin.getInstance().getLogger().severe("An error occured when save the file configuration.");
                e.printStackTrace();
                return;
            }
        }

        // @Note Set the configuration.
        FactionsConfiguration.setInstance(configuration);
    }

    /**
     * This method de-initialize the main registries that the Factions Plugin must be do.
     * @since 1.0
     */
    public void deInitRegistry() {

    }
}
