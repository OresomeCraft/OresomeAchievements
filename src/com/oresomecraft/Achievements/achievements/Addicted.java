package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class Addicted extends OAchievement implements IOAchievement, Listener {

    public Addicted() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Addicted";
    OAType type = OAType.INCREMENTAL;
    String criteria = "You've gotta be addicted to come back here more than 100 times!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkJoin(PlayerJoinEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        int increment = 0;
        if(config.contains(event.getPlayer().getName()+".increments.addicted") == true){
            increment = config.getInt(event.getPlayer().getName()+".increments.addicted");
            config.set(event.getPlayer().getName()+".increments.addicted", increment + 1);
        }else{
            config.set(event.getPlayer().getName()+".increments.addicted", 1);
        }
        if(increment >= 100){
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward, config);
        }
        if(increment == 50 || increment == 25 || increment == 75 || increment == 90){
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
