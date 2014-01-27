package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AchievementPlayer {

    public AchievementPlayer() {
    }

    // Local achievement stuff
    private static ArrayList<String> completed = new ArrayList<String>();

    /**
     * Add another achievement to the local cache
     */
    public void addNewAchievement(String achievement) {
        completed.add(achievement);
    }

    /**
     * Get the player's list of completed achievements
     */
    public ArrayList<String> getCompletedAchievements() {
        return completed;
    }

    /**
     * Gets a player's AchievementPlayer from their username
     */
    public static AchievementPlayer getAchievementPlayer(String name) {
        return OresomeAchievements.getInstance().getAchievementPlayers().get(name);
    }

    /**
     * Get's a players completed achievements and caches them
     *
     * @param cache A list of completed achievements
     */
    public void setCompleted(ArrayList<String> cache) {
        completed.clear();
        completed.addAll(cache);
    }

    /**
     * Gets a player's AchievementPlayer from their player object
     */
    public static AchievementPlayer getAchievementPlayer(Player p) {
        return OresomeAchievements.getInstance().getAchievementPlayers().get(p.getName());
    }

    /**
     * Creates a BattlePlayer for a Player
     *
     * @param p A Player
     */
    public static void craftAchievementPlayer(Player p) {
        AchievementPlayer ap = new AchievementPlayer();
        OresomeAchievements.getInstance().getAchievementPlayers().put(p.getName(), ap);
    }
}