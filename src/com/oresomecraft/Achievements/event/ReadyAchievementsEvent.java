package com.oresomecraft.Achievements.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Empty event that adds all the achievement objects.
 */
public final class ReadyAchievementsEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}