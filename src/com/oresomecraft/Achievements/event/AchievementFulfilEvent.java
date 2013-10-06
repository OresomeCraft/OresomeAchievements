package com.oresomecraft.Achievements.event;

import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OresomeAchievements;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;

public final class AchievementFulfilEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String name;
    private int increment;
    private String criteria;
    private int award;
    private OAType type;

    public AchievementFulfilEvent(Player player, String name, int increment, String criteria, int award, OAType type) {
        this.player = player;
        this.name = name;
        this.increment = increment;
        this.criteria = criteria;
        this.award = award;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public YamlConfiguration getConfig() {
        YamlConfiguration c = null;
        for (Map.Entry<String, YamlConfiguration> entry : OresomeAchievements.getInstance().getUserConfigs().entrySet()) {
            if (entry.getKey().equals(player.getName()))
                c = entry.getValue();
        }
        return c;
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