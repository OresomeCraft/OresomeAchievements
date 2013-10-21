package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SQLListener implements Listener {

    OresomeAchievements plugin;

    public SQLListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    /**
     * Public listener to listen into and change all the SQL statistics for users
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        SQLAccess.queryAddUser(event.getPlayer().getName());
        SQLAccess.queryIncrementJoins(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void breaks(BlockBreakEvent event) {
        SQLAccess.queryIncrementBreaks(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void places(BlockPlaceEvent event) {
        SQLAccess.queryIncrementPlaces(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void visit(PlayerTeleportEvent event) {
        if (event.getFrom().getWorld().getName().equals(event.getTo().getWorld().getName())) return;
        SQLAccess.queryIncrementVisits(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void kick(PlayerKickEvent event) {
        if (event.getReason().contains("Flying is not enabled on this server")) {
            SQLAccess.querySetKickedReason(event.getPlayer().getName(), "truefly");
        } else {
            SQLAccess.querySetKickedReason(event.getPlayer().getName(), "true");
        }
        //Create a new config for the player, if the player doesn't have one.
    }
}
