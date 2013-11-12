package com.oresomecraft.Achievements;

import com.oresomecraft.Achievements.event.AchievementCheckpointEvent;
import com.oresomecraft.Achievements.event.AchievementFulfilEvent;
import com.oresomecraft.Achievements.event.ReadyAchievementsEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class OAchievement implements Listener {

    public OresomeAchievements plugin = OresomeAchievements.getInstance();
    public OAchievement config;

    public OAchievement() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // Achievement Details
    String name;
    OAType type;
    String criteria;
    int award;

    /**
     * Readies achievements
     *
     * @param event An Event called by the plugin
     */
    @EventHandler // Add achievement
    public void readyAch(ReadyAchievementsEvent event) {
        addAchievement(name);
        addCriteria(name, criteria);
    }

    /**
     * Sets details for the achievement after initiation
     *
     * @param config An OAchievement
     * @param name   Name of map
     */
    protected final void initiate(OAchievement config, String name, OAType type, String criteria, int award) {
        this.config = config;
        this.name = name;
        this.type = type;
        this.criteria = criteria;
        this.award = award;
    }

    protected final void callAchievementGet(String name, OAType type, String criteria, Player player, int increment, int reward) {
        Bukkit.getPluginManager().callEvent(new AchievementFulfilEvent(player, name, increment, criteria, reward, type));
    }

    protected final void callAchievementCheckpoint(String name, OAType type, String criteria, Player player, int increment) {
        Bukkit.getPluginManager().callEvent(new AchievementCheckpointEvent(player, name, increment, criteria, type));
    }

    private static void addAchievement(String name) {
        OresomeAchievements.addAchievement(name);
    }

    private static void addCriteria(String name, String criteria) {
        OresomeAchievements.addCriteria(name, criteria);
    }

}
