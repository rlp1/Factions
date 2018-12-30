package io.github.rlp.factions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rlp (19/12/2018 15:06)
 * @since 1.0
 */
public abstract class FactionsCommand extends Command {

    public FactionsCommand(final String name, final String... aliases) {
        this(name, aliases == null ? Collections.emptyList() : Arrays.asList(aliases));
    }

    public FactionsCommand(String name, List<String> aliases) {
        super(name, "", "", aliases);
    }

    /**
     * This represents an "internal" method that is called by the Bukkit/Spigot handlers. Then, this method check if
     * the sender can execute this command, if can, execute command, otherwise return it.
     *
     * @param sender the command sender.
     * @param args the arguments from the command.
     */
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        // @Note Check if the player can executee the command.
        if (!this.canExecute(sender)) return false;

        // @Note This method execute the command.
        this.execute(sender, args);
        return false;
    }

    /**
     * This method represents the functionality about check if the command sender can execute the command.
     *
     * @param sender the command sender.
     * @return true, if the player can execute the command, otherwise returns false.
     *
     * @since 1.0
     */
    public boolean canExecute(final CommandSender sender) {
        return true;
    }

    /**
     * This represents the functions of the command.
     *
     * @param sender the command sender.
     * @param args the arguments from command.
     *
     * @since 1.0
     */
    public abstract void execute(final CommandSender sender, final String[] args);
}
