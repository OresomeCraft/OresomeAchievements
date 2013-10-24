package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class GoneFishin extends OAchievement implements IOAchievement, Listener {

    public GoneFishin() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Gone Fishin'";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Catch 5 fish!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkFish(PlayerFishEvent event) {
        if (event.getCaught() != null){
            callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward);;
        }
    }
}
