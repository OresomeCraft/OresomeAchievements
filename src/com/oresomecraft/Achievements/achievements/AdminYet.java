package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class AdminYet extends OAchievement implements IOAchievement, Listener {

    public AdminYet() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Do I get admin yet?";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Kill zachoz";
    int reward = 1;
    //Lol, it's a legit achievement @Zachoz

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getName().equals("zachoz")) {
            callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward, ConfigAccess.loadUserConfig(event.getEntity().getKiller().getName()));
        }
    }
}
