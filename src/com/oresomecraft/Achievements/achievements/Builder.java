package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Map;

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
        int increment = SQLAccess.queryGetPlaces(event.getPlayer().getName());
        if (increment >= 1000) {
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if (increment == 500 || increment == 250 || increment == 750 || increment == 900) {
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
    }
}
