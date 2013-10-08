package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SQLListener implements Listener {

    OresomeAchievements plugin;

    public SQLListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    /**
     * Public listener to listen into and change all the SQL statistics for users
     */

    @EventHandler (priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        SQLAccess.queryAddUser(event.getPlayer().getName());
        SQLAccess.queryIncrementJoins(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }
}
