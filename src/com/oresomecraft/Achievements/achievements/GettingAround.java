package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;

public class GettingAround extends OAchievement implements IOAchievement, Listener {

    public GettingAround() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Getting Around";
    OAType type = OAType.INCREMENTAL;
    String criteria = "View 25 different maps!";
    int reward = 30;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkWorld(PlayerTeleportEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if (ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        List<String> list = config.getStringList(event.getPlayer().getName() + ".lists.visited");
        if (list.contains(event.getTo().getWorld().getName())) return;
        list.add(event.getTo().getWorld().getName());
        config.set(event.getPlayer().getName() + ".lists.visited", list);
        if (list.size() >= 20) {
            callAchievementGet(name, type, criteria, event.getPlayer(), list.size(), reward);
        }
        if (list.size() == 5 || list.size() == 20) {
            callAchievementCheckpoint(name, type, criteria, event.getPlayer(), list.size());
        }
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
}
