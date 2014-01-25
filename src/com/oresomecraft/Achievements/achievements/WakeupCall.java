package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@Achievement
public class WakeupCall extends OAchievement implements IOAchievement, Listener {

    public WakeupCall() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Wake-up Call";
    OAType type = OAType.OBJECTIVE;
    String criteria = "What happens when you sleep in the nether? You'll find out!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

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
