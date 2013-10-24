package com.oresomecraft.Achievements.event;

import com.oresomecraft.Achievements.OAType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public final class AchievementFulfilEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String name;
    private int increment;
    private String criteria;
    private int award;
    private OAType type;

    /**
     * Called when a player completes an achievement
     *
     * @param player    A Player
     * @param name      The achievement name
     * @param increment The level indicating how complete an achievement is
     * @param criteria  The criteria required to complete the achievement
     * @param award     The reward given to the player in return for completing the achievement
     * @param type      The achievement type
     */
    public AchievementFulfilEvent(Player player, String name, int increment, String criteria, int award, OAType type) {
        super(player);
        this.name = name;
        this.increment = increment;
        this.criteria = criteria;
        this.award = award;
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

    /**
     * @return The achievement reward
     */
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