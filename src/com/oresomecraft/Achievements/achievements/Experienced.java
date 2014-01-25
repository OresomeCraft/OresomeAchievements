package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

@Achievement
public class Experienced extends OAchievement implements IOAchievement, Listener {

    public Experienced() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Experienced";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Getting to level 30 in a single map? Impressive!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerLevelChangeEvent event) {
        if (event.getNewLevel() >= 30) {
            callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward);
        }
    }
}
