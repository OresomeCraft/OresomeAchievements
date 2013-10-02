package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class N00k extends OAchievement implements IOAchievement, Listener {

    public N00k() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "N00k";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Throw a spire nuke!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkHit(ProjectileHitEvent event) {
        if (event.getEntity().getWorld().getName().equalsIgnoreCase("spire")) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player p = (Player) event.getEntity().getShooter();
                callAchievementGet(name, type, criteria, (Player) event.getEntity().getShooter(), 0, reward, ConfigAccess.loadUserConfig(p.getName()));
            }
        }
    }
}
