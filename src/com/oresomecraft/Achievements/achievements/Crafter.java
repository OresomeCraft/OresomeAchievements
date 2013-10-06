package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.Map;

public class Crafter extends OAchievement implements IOAchievement, Listener {

    public Crafter() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Crafter";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Craft 100 items!";
    int reward = 30;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkPlace(CraftItemEvent event) {
        Player p = (Player) event.getWhoClicked();
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(p.getName()))
                config = entry.getValue();
        }
        if (config == null) return;
        int increment = 0;
        if (config.contains(p.getName() + ".increments.crafter") == true) {
            increment = config.getInt(p.getName() + ".increments.crafter");
            config.set(p.getName() + ".increments.crafter", increment + 1);
        } else {
            config.set(p.getName() + ".increments.crafter", 1);
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
