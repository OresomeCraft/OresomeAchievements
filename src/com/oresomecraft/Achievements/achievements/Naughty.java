package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

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
    @EventHandler
    public void checkKick(PlayerKickEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        config.set(event.getPlayer().getName()+".checks.kicked", true);
        ConfigAccess.saveUserConfig(config, event.getPlayer().getName());
    }
    @EventHandler
    public void checkJoin(PlayerJoinEvent event) {
        //Players may not have a config, just add a fail-safe check.
        if(ConfigAccess.userConfigExists(event.getPlayer().getName()) == false) return;
        YamlConfiguration config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        if(config.getBoolean(event.getPlayer().getName()+".checks.kicked") == true){
            callAchievementGet(name, type, criteria, event.getPlayer(), 0, reward, config);
        }
    }
}
