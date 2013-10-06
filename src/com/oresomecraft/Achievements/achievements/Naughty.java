package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.Map;

public class Naughty extends OAchievement implements IOAchievement, Listener {

    public Naughty() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Naughty";
    OAType type = OAType.CHECK;
    String criteria = "Get kicked from the server!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler(priority = EventPriority.LOWEST)
    public void checkKick(PlayerKickEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(event.getPlayer().getName()))
                config = entry.getValue();
        }
        if(config == null) return;
        config.set(event.getPlayer().getName()+".checks.kicked", true);
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
    @EventHandler(priority = EventPriority.LOWEST)
             public void checkJoin(PlayerJoinEvent event) {
        timer(event.getPlayer());
    }

    public void timer(final Player p){
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
            public void run(){
                //Players may not have a config, just add a fail-safe check.
                if(ConfigAccess.userConfigExists(p.getName()) == false) return;
                YamlConfiguration config = null;
                for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
                    if (entry.getKey().equals(p.getName()))
                        config = entry.getValue();
                }
                if(config == null) return;
                if(config.getBoolean(p.getName()+".checks.kicked") == true){
                    callAchievementGet(name, type, criteria, p, 0, reward);
                }
            }
        }, 20L);
    }
}
