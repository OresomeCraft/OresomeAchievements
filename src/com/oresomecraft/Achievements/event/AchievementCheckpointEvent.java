package com.oresomecraft.Achievements.event;

import com.oresomecraft.Achievements.OAType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class AchievementCheckpointEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String name;
    private int increment;
    private String criteria;
    private OAType type;

    public AchievementCheckpointEvent(Player player, String name, int increment, String criteria, OAType type) {
        this.player = player;
        this.name = name;
        this.increment = increment;
        this.criteria = criteria;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
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

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}