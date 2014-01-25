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
public class Cursed extends OAchievement implements IOAchievement, Listener {

    public Cursed() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Cursed";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Damage potions are more harmful than they seem!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkEntity(PlayerDeathEvent event) {
        DamageCause cause = event.getEntity().getLastDamageCause().getCause();
        if (cause == DamageCause.MAGIC) {
            callAchievementGet(name, type, criteria, event.getEntity(), 0, reward);
        }
    }
}
