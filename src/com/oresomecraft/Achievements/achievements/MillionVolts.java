package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

@Achievement
public class MillionVolts extends OAchievement implements IOAchievement, Listener {

    public MillionVolts() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Million Volts";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Out of all the places it could've struck...";
    int reward = 25;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkEntity(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            try {
                DamageCause cause = p.getLastDamageCause().getCause();
                if (cause == DamageCause.LIGHTNING) {
                    callAchievementGet(name, type, criteria, p, 0, reward);
                }
            } catch (NullPointerException e) {
                //Something goes wrong sometimes. Just ignore the error and get on with it.
            }
        }
    }
}
