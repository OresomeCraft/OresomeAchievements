package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

public class Addicted extends OAchievement implements IOAchievement, Listener {

    public Addicted() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Addicted";
    OAType type = OAType.INCREMENTAL;
    String criteria = "You've gotta be addicted to come back here more than 100 times!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler (priority = EventPriority.HIGHEST)
    public void checkJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        //Players may not have a config, just add a fail-safe check.
        if (ConfigAccess.userConfigExists(p.getName()) == false) return;
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(p.getName()))
                config = entry.getValue();
        }
        if (config == null) return;
        int increment = 0;
        if (config.contains(p.getName() + ".increments.addicted") == true) {
            increment = config.getInt(p.getName() + ".increments.addicted");
            config.set(p.getName() + ".increments.addicted", increment + 1);
        } else {
            config.set(p.getName() + ".increments.addicted", 1);
        }
        if (increment >= 100) {
            callAchievementGet(name, type, criteria, p, increment, reward);
        }
        if (increment == 50 || increment == 25 || increment == 75 || increment == 90) {
            callAchievementCheckpoint(name, type, criteria, p, increment);
        }
        ConfigAccess.saveUserConfig(config, p.getName());
    }
}
