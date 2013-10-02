package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Manipulator extends OAchievement implements IOAchievement, Listener {

    public Manipulator() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Manipulator";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Toggle the gravity manipulator!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkBreal(BlockBreakEvent event) {
        if (event.getBlock().getWorld().getName().equalsIgnoreCase("gravity") && event.getBlock().getType() == Material.OBSIDIAN) {
            callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward, ConfigAccess.loadUserConfig(event.getPlayer().getName()));
        }
    }
}
