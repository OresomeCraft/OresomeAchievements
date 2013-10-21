package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Naughty extends OAchievement implements IOAchievement, Listener {

    public Naughty() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Naughty";
    OAType type = OAType.CHECK;
    String criteria = "Get kicked from the server!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void checkJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (config.getBoolean(p.getName() + ".checks.kicked") == true) {
            callAchievementGet(name, type, criteria, p, 0, reward);
        }
    }
}
