package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class JoinListener implements Listener {

    OresomeAchievements plugin;

    public JoinListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void checkConfigOnJoin(PlayerJoinEvent event) {
        if (new File("plugins/OresomeAchievements/users/", event.getPlayer().getName() + ".yml").isFile()) {
            return;
        }
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.save("plugins/OresomeAchievements/users/" + event.getPlayer().getName() + ".yml");
        } catch (IOException e) {
            e.printStackTrace();  //Meh, this isn't retard proof.
        }
        //Create a new config for the player, if the player doesn't have one.
    }
}
