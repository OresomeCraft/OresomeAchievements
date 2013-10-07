package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JoinListener implements Listener {

    OresomeAchievements plugin;

    public JoinListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkConfigOnJoin(PlayerJoinEvent event) {
        SQLAccess.queryAddUser(event.getPlayer().getName());
        //Create a new config for the player, if the player doesn't have one.
    }
}
