package io.github.rlp.factions.register;

import com.google.common.base.Preconditions;

import io.github.rlp.factions.command.FactionCommand;
import io.github.rlp.factions.command.FactionsCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

/**
 * @author rlp
 * @since 1.0
 */
public final class FactionsRegistryCommand extends FactionsRegistry {

    /**
     * This represents the command map that register the commands.
     * @since 1.0
     */
    private final CommandMap commandMap = this.getCommandMap();

    @Override
    public void initRegistry() {
        // @Note This represents the main command of the factions.
        this.registerCommand(new FactionCommand());
    }

    /**
     * This method register the command into the game.
     * @param command the command, that will be registered.
     * @since 1.0
     */
    public void registerCommand(final FactionsCommand command) {
        Preconditions.checkNotNull(command, "command");

        this.commandMap.register(command.getName(), command);
    }

    /**
     * This method unregister the command from the game.
     * @param commandName the command name.
     * @since 1.0
     */
    public void unregisterCommand(final String commandName) {
        throw new UnsupportedOperationException("This operation is not supported ,yet.");
    }

    /**
     * This method get the command map that register the commands.
     * @return the command map.
     * @since 1.0
     */
    private CommandMap getCommandMap() {
        final CommandMap commandMap;

        try {
            final Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");

            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getPluginManager());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occured when get \"CommandMap\" class to register the commands.");
        }

        return commandMap;
    }
}
