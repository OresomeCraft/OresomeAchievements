package com.oresomecraft.Achievements.event;

import com.oresomecraft.Achievements.OAType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public final class AchievementCheckpointEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String name;
    private int increment;
    private String criteria;
    private OAType type;

    /**
     * Called when a player completes an achievement checkpoint
     *
     * @param player    A Player
     * @param name      The achievement name
     * @param increment The level indicating how complete an achievement is
     * @param criteria  The criteria required to complete the achievement
     * @param type      The achievement type
     */
    public AchievementCheckpointEvent(Player player, String name, int increment, String criteria, OAType type) {
        super(player);
        this.name = name;
        this.increment = increment;
        this.criteria = criteria;
        this.type = type;
    }

    /**
     * @return The achievement name
     */
    public String getAchievementName() {
        return name;
    }

    /**
     * @return The achievement type
     */
    public OAType getType() {
        return type;
    }

    /**
     * @return The achievement criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @return The achievement increment
     */
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