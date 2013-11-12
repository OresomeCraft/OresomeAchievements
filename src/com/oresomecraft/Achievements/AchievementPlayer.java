package com.oresomecraft.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AchievementPlayer extends CraftPlayer {

    public AchievementPlayer(Player p) {
        super((CraftServer) Bukkit.getServer(), ((CraftPlayer) p).getHandle());
    }

    // Local achievement stuff
    private static ArrayList<String> completed = new ArrayList<String>();

    /**
     * Add another achievement to the local cache
     */
    public static void addNewAchievement(String achievement) {
        completed.add(achievement);
    }

    /**
     * Get the player's list of completed achievements
     */
    public static ArrayList<String> getCompletedAchievements() {
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
    public static void setCompleted(ArrayList<String> cache) {
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
        AchievementPlayer ap = new AchievementPlayer(p);
        OresomeAchievements.getInstance().getAchievementPlayers().put(p.getName(), ap);
    }

    public boolean hasPermisson(String perm) {
        return Bukkit.getPlayer(getName()).hasPermission(perm);
    }


    // *********** Deprecated thanks to Bukkit's overmapped thing ***********

    public double getLastDamage() {
        return super.getLastDamage();
    }

    public double getMaxHealth() {
        return super.getMaxHealth();
    }

    public double getHealth() {
        return super.getHealth();
    }

    @Deprecated
    public void setMaxHealth(int amount) {
        super.setMaxHealth((double) amount);
    }

    @Deprecated
    public void setHealth(int amount) {
        // This is a dud. Use setHealth(double)
    }

    @Deprecated
    public void damage(int amount) {
        super.damage((double) amount);
    }

    @Deprecated
    public void damage(int amount, org.bukkit.entity.Entity entity) {
        super.damage((double) amount, entity);
    }

    @Deprecated
    public void setLastDamage(int amount) {
        super.damage((double) amount);
    }

}