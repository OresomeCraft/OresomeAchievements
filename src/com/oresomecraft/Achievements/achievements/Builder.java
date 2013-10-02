package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Builder extends OAchievement implements IOAchievement, Listener {

    public Builder() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Builder";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Place 1000 blocks!";
    int reward = 30;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkPlace(BlockPlaceEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        int increment = 0;
        if(config.contains(event.getPlayer().getName()+".increments.builder") == true){
            increment = config.getInt(event.getPlayer().getName()+".increments.builder");
            config.set(event.getPlayer().getName()+".increments.builder", increment + 1);
        }else{
            config.set(event.getPlayer().getName()+".increments.builder", 1);
        }
        if(increment >= 1000){
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward, config);
        }
        if(increment == 500 || increment == 250 || increment == 750 || increment == 900){
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
