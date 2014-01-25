package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@Achievement
public class Ranked extends OAchievement implements IOAchievement, Listener {

    public Ranked() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "#Rank";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Get some extra swag by donating!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().getDisplayName().contains("#")) {
            callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward);
        }
    }
}
