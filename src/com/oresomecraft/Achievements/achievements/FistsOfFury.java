package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FistsOfFury extends OAchievement implements IOAchievement, Listener {

    public FistsOfFury() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Fists of Fury";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Kill another player with your fists!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player && event.getEntity().getKiller().getItemInHand().getType() == Material.AIR) {
            callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
        }
    }
}
