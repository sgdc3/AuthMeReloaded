package fr.xephi.authme.permission;

import fr.xephi.authme.command.CommandDescription;
import org.bukkit.entity.Player;

/**
 * Interface for dealing with permissions.
 */
public interface PermissionsService {

    /**
     * Check if the player has the given permission.
     *
     * @param player     The player
     * @param permission The permission node to check
     * @param def        Default returned if no permissions system is used
     *
     * @return True if the player has permission
     */
    boolean hasPermission(Player player, PermissionNode permission, boolean def);

    /**
     * Check if the player has the permissions for the given command.
     *
     * @param player  The player
     * @param command The command whose permissions should be checked
     *
     * @return True if the player may execute the command
     */
    boolean hasPermission(Player player, CommandDescription command);

    /**
     * Return the permission system the service is working with.
     *
     * @return The permission system AuthMe is hooked into
     */
    PermissionsSystemType getSystem();

}
