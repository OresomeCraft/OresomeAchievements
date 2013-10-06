package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Map;

public class Demolition extends OAchievement implements IOAchievement, Listener {

    public Demolition() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Demolition";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Break 1000 blocks!";
    int reward = 30;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkPlace(BlockBreakEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(event.getPlayer().getName()))
                config = entry.getValue();
        }
        if(config == null) return;
        int increment = 0;
        if(config.contains(event.getPlayer().getName()+".increments.demolition") == true){
            increment = config.getInt(event.getPlayer().getName()+".increments.demolition");
            config.set(event.getPlayer().getName()+".increments.demolition", increment + 1);
        }else{
            config.set(event.getPlayer().getName()+".increments.demolition", 1);
        }
        if(increment >= 1000){
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if(increment == 500 || increment == 250 || increment == 750 || increment == 900){
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
