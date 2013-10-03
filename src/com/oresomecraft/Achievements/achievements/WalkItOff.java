package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WalkItOff extends OAchievement implements IOAchievement, Listener {

    public WalkItOff() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Walk it off";
    OAType type = OAType.INCREMENTAL;
    String criteria = "Take over 100 damage without dying!";
    int reward = 30;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player)event.getEntity();
            YamlConfiguration config = ConfigAccess.loadUserConfig(p.getName());
            int increment = 0;
            if (config.contains(p.getName() + ".increments.damage") == true) {
                increment = config.getInt(p.getName() + ".increments.damage");
                config.set(p.getName() + ".increments.damage", increment + event.getDamage());
            } else {
                config.set(p.getName() + ".increments.damage", event.getDamage());
            }
            if (increment >= 100) {
                callAchievementGet(name, type, criteria, p, increment, reward, config);
            }
            if (increment >= 50 == increment <= 60) {
                callAchievementCheckpoint(name, type, criteria, p, increment);
            }
            ConfigAccess.saveUserConfig(config, p.getName());
        }
    }

    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
            Player p = event.getEntity();
            YamlConfiguration config = ConfigAccess.loadUserConfig(p.getName());
            if (config.contains(p.getName() + ".increments.damage") == true) {
                config.set(p.getName() + ".increments.damage", 0);
            } else {
                //No config yet.
            }
            ConfigAccess.saveUserConfig(config, p.getName());
    }
}
