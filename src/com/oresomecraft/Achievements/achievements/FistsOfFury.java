package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

@Achievement
public class FistsOfFury extends OAchievement implements IOAchievement, Listener {

    public FistsOfFury() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Fists of Fury";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Taste my fists, bitch!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null && event.getEntity().getKiller().getItemInHand().getType() == Material.AIR) {
            if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                //Whew, that's one long method.
                callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
            }
        }
    }
}
