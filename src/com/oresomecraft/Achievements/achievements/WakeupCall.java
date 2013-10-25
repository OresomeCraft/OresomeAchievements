package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WakeupCall extends OAchievement implements IOAchievement, Listener {

    public WakeupCall() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Wake-up Call";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Sleep in a bed in a hell biome!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.BED_BLOCK)) {
                if (event.getClickedBlock().getBiome().toString().equals("HELL")) {
                    callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward);
                }
            }
        }
    }
}
