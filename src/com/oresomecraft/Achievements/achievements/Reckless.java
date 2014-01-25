package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

@Achievement
public class Reckless extends OAchievement implements IOAchievement, Listener {

    public Reckless() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Reckless";
    OAType type = OAType.OBJECTIVE;
    String criteria = "My brand new minecart is ruined!";
    int reward = 15;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(VehicleDestroyEvent event) {
        if (event.getVehicle().getPassenger() instanceof Player) {
            Player p = (Player) event.getVehicle().getPassenger();
            callAchievementGet(name, type, criteria, p, 0, reward);
        }
    }
}
