package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sturdy extends OAchievement implements IOAchievement, Listener {

    public Sturdy() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Sturdy";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Kill a player whilst on half a heart!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity().getKiller().getHealth() == 1) {
                Player p = event.getEntity().getKiller();
                callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
            }
        }
    }
}
