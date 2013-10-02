package com.oresomecraft.Achievements.event;

import com.oresomecraft.Achievements.OAType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class AchievementFulfilEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String name;
    private int increment;
    private String criteria;
    private int award;
    private OAType type;
    private YamlConfiguration config;

    public AchievementFulfilEvent(Player player, String name, int increment, String criteria, int award, OAType type, YamlConfiguration config) {
        this.player = player;
        this.name = name;
        this.increment = increment;
        this.criteria = criteria;
        this.award = award;
        this.type = type;
        this.config = config;
    }

    public Player getPlayer() {
        return player;
    }

    public YamlConfiguration getConfig(){
        return config;
    }

    public String getAchievementName() {
        return name;
    }

    public OAType getType() {
        return type;
    }

    public String getCriteria() {
        return criteria;
    }

    public int getIncrement() {
        return increment;
    }

    public int getReward() {
        return award;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}