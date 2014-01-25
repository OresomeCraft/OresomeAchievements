package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Achievement
public class Overkill extends OAchievement implements IOAchievement, Listener {

    public Overkill() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Overkill";
    OAType type = OAType.OBJECTIVE;
    String criteria = "How can you even deal that much damage?!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getDamage() >= 12) {
            callAchievementGet(name, type, criteria, (Player) event.getDamager(), 0, reward);
        }
    }
}
