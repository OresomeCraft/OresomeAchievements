package com.oresomecraft.Achievements;

public class Access {

    public static void addAchievement(String name) {
        OresomeAchievements.addAchievement(name);
    }

    public static void addCriteria(String name, String criteria) {
        OresomeAchievements.addCriteria(name, criteria);
    }

    public static void awardPoints(org.bukkit.entity.Player p, int points) {
        //Code to award OresomeCraft Points (BattlePoints) on fulfilling a mission.
    }
}
