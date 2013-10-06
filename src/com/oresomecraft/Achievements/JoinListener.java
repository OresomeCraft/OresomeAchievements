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
        YamlConfiguration config = new YamlConfiguration();
        if (new File("plugins/OresomeAchievements/users/", event.getPlayer().getName() + ".yml").isFile()) {
            config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
            OresomeAchievements.getUserConfigs().put(event.getPlayer().getName(), config);
            OresomeAchievements.getReady().add(event.getPlayer().getName());
            return;
            //Create an instance of the user's config.
        }
        try {
            config.save("plugins/OresomeAchievements/users/" + event.getPlayer().getName() + ".yml");
            OresomeAchievements.getUserConfigs().put(event.getPlayer().getName(), config);
            OresomeAchievements.getReady().add(event.getPlayer().getName());
        } catch (IOException e) {
            e.printStackTrace();  //Meh, this isn't retard proof.
        }
        //Create a new config for the player, if the player doesn't have one.
    }

    @EventHandler
    public void checkConfigOnLeave(PlayerQuitEvent event) {
        try {
            OresomeAchievements.getInstance().getUserConfigs().remove(event.getPlayer().getName());
            OresomeAchievements.getReady().remove(event.getPlayer().getName());
        } catch (NullPointerException e) {
            //nullnullnullnull
        }
        //Remove instance of the user's config.
    }
}
