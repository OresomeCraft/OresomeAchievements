package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import java.util.Map;

public class GoneFishin extends OAchievement implements IOAchievement, Listener {

    public GoneFishin() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Gone Fishin'";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Catch 5 fish!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkFish(PlayerFishEvent event) {
        if(event.getCaught() == null) return;
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(event.getPlayer().getName()))
                config = entry.getValue();
        }
        if(config == null) return;
        int increment = 0;
        if(config.contains(event.getPlayer().getName()+".increments.fishing") == true){
            increment = config.getInt(event.getPlayer().getName()+".increments.fishing");
            config.set(event.getPlayer().getName()+".increments.fishing", increment + 1);
        }else{
            config.set(event.getPlayer().getName()+".increments.fishing", 1);
        }
        if(increment >= 5){
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if(increment == 3){
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
