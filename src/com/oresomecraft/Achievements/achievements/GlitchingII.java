package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GlitchingII extends OAchievement implements IOAchievement, Listener {

    public GlitchingII() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Glitching II";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Interact with an air block!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.AIR){
        callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward);
        }
    }
}
