package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import com.oresomecraft.Achievements.SQLAccess;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class FreeTransport extends OAchievement implements IOAchievement, Listener {

    //Objective details
    String name = "Free Transport";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Get teleported between worlds 500 times!";
    int reward = 20;

    public FreeTransport() {
        super.initiate(this, name, type, criteria, reward);
    }

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkWorld(PlayerTeleportEvent event) {
        int increment = SQLAccess.queryGetVisits(event.getPlayer().getName());
        if (increment >= 500) {
            callAchievementGet(name, type, criteria, event.getPlayer(), increment, reward);
        }
        if (increment == 50 || increment == 150 || increment == 350 || increment == 475) {
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), increment);
        }
    }
}
