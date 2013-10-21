package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import com.oresomecraft.Achievements.SQLAccess;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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
    public void checkBreak(BlockBreakEvent event) {
        int increment = SQLAccess.queryGetBreaks(event.getPlayer().getName());
        if (increment >= 1000) {
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if (increment == 500 || increment == 250 || increment == 750 || increment == 900) {
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
    }
}
