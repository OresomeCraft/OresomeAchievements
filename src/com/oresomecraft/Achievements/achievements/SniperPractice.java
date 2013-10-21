package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SniperPractice extends OAchievement implements IOAchievement, Listener {

    public SniperPractice() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Sniper Practice";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Shoot and kill a player from over 40 blocks away!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        if (event.getEntity().getLocation().distance(event.getEntity().getKiller().getLocation()) >= 40) {
            callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
        }
    }
}
