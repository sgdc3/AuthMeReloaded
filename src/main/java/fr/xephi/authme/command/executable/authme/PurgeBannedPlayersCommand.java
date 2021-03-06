package fr.xephi.authme.command.executable.authme;

import fr.xephi.authme.AuthMe;
import fr.xephi.authme.command.CommandParts;
import fr.xephi.authme.command.ExecutableCommand;
import fr.xephi.authme.settings.Settings;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PurgeBannedPlayersCommand extends ExecutableCommand {

    /**
     * Execute the command.
     *
     * @param sender           The command sender.
     * @param commandReference The command reference.
     * @param commandArguments The command arguments.
     *
     * @return True if the command was executed successfully, false otherwise.
     */
    @Override
    public boolean executeCommand(CommandSender sender, CommandParts commandReference, CommandParts commandArguments) {
        // AuthMe plugin instance
        final AuthMe plugin = AuthMe.getInstance();

        // Get the list of banned players
        List<String> bannedPlayers = new ArrayList<>();
        for (OfflinePlayer offlinePlayer : plugin.getServer().getBannedPlayers()) {
            bannedPlayers.add(offlinePlayer.getName().toLowerCase());
        }

        // Purge the banned players
        plugin.database.purgeBanned(bannedPlayers);
        if (Settings.purgeEssentialsFile && plugin.ess != null)
            plugin.dataManager.purgeEssentials(bannedPlayers);
        if (Settings.purgePlayerDat)
            plugin.dataManager.purgeDat(bannedPlayers);
        if (Settings.purgeLimitedCreative)
            plugin.dataManager.purgeLimitedCreative(bannedPlayers);
        if (Settings.purgeAntiXray)
            plugin.dataManager.purgeAntiXray(bannedPlayers);

        // Show a status message
        sender.sendMessage("[AuthMe] Database has been purged correctly");
        return true;
    }
}
