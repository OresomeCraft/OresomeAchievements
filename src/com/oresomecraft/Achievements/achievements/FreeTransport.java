package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Map;

public class FreeTransport extends OAchievement implements IOAchievement, Listener {

    public FreeTransport() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Free Transport";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Get teleported between worlds 500 times!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkWorld(PlayerTeleportEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(event.getPlayer().getName()))
                config = entry.getValue();
        }
        if(config == null) return;
        int increment = 0;
        if(event.getFrom().getWorld().getName().equals(event.getTo().getWorld().getName())) return;
        if(config.contains(event.getPlayer().getName()+".increments.transport") == true){
            increment = config.getInt(event.getPlayer().getName()+".increments.transport");
            config.set(event.getPlayer().getName()+".increments.transport", increment + 1);
        }else{
            config.set(event.getPlayer().getName()+".increments.transport", 1);
        }
        if(increment >= 500){
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if(increment == 50 || increment == 150 || increment == 350 || increment == 475){
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
