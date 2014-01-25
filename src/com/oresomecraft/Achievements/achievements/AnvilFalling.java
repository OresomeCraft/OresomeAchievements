package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

@Achievement
public class AnvilFalling extends OAchievement implements IOAchievement, Listener {

    public AnvilFalling() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Anvil Falling";
    OAType type = OAType.OBJECTIVE;
    String criteria = "How far can you fall?";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause().getCause() == DamageCause.FALL && event.getEntity().getFallDistance() >= 200) {
            callAchievementGet(name, type, criteria, event.getEntity(), 0, reward);
        }
    }
}
