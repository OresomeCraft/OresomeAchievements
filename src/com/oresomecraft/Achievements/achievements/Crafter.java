package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import com.oresomecraft.Achievements.SQLAccess;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class Crafter extends OAchievement implements IOAchievement, Listener {

    //Objective details
    String name = "Crafter";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Craft 100 items!";
    int reward = 30;

    public Crafter() {
        super.initiate(this, name, type, criteria, reward);
    }

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkPlace(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player p = (Player) event.getWhoClicked();
            int increment = SQLAccess.queryGetCrafts(p.getName());
            if (increment >= 100) {
                callAchievementGet(name, type, criteria, p, increment, reward);
            }
            if (increment == 50 || increment == 25 || increment == 75 || increment == 90) {
                callAchievementCheckpoint(name, type, criteria, p, increment);
            }
        }
    }
}
