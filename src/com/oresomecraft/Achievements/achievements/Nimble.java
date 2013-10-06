package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Nimble extends OAchievement implements IOAchievement, Listener {

    public Nimble() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Nimble";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Die from less than 4 blocks fall damage!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause().getCause() == DamageCause.FALL && event.getEntity().getFallDistance() < 5) {
            callAchievementGet(name, type, criteria, event.getEntity(), 0, reward);
        }
    }
}
