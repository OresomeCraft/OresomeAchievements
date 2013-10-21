package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GlitchingIII extends OAchievement implements IOAchievement, Listener {

    public GlitchingIII() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Glitching III";
    OAType type = OAType.CHECK;
    String criteria = "Get kicked for hovering too long!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    public void checkJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        //Players may not have a config, just add a fail-safe check.
        if (config.getBoolean(p.getName() + ".checks.kickedhovering") == true) {
            callAchievementGet(name, type, criteria, p, 0, reward);
        }
    }
}
