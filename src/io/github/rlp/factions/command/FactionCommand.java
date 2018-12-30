package io.github.rlp.factions.command;

import io.github.rlp.factions.FactionsPlugin;
import io.github.rlp.factions.faction.Faction;
import io.github.rlp.factions.gui.GUI;
import io.github.rlp.factions.gui.custom.PlayerInfoGUI;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author rlp
 * @since 1.0
 */
public final class FactionCommand extends FactionsCommand {

    public FactionCommand() {
        super("faction", "f");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // @Note Check if the sender is not a player.
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDesculpe, más, somente jogadores podem executar este comando.");
            return;
        }

        final Player player = (Player) sender;

        // @Note If the arguments length is equals 0, then show the Faction GUI.
        if (args.length == 0) {
            return;
        }

        final String input = args[0];

        // @Note This input make that the input represents a faction tag.
        if (input.length() == 3) {
            // @Note Get the selected faction by the input.
            final Faction selectedFaction = FactionsPlugin.REGISTRY.getFactionsRegistry().getFaction(input);

            if (selectedFaction == null) {
                player.sendMessage("§cA facção com a tag \"" + input + "\" não foi encontrada. Tem certeza que é esta?");
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1f, 1f);
                return;
            }

            // @Note Add the GUI about guild information.
        }
        // @Note This represents that the input is a player name.
        else {
            GUI.openGUI(new PlayerInfoGUI(player, input));
        }
    }
}
