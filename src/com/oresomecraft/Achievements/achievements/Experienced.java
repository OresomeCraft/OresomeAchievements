package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class Experienced extends OAchievement implements IOAchievement, Listener {

    public Experienced() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Experienced";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Get 30 levels of EXP on any map!";
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
