package com.oresomecraft.Achievements.persistence.query;

import com.oresomecraft.Achievements.OresomeAchievements;

public abstract class Query {
    public abstract String createCompleteTable();

    public abstract String createTemporaryCompleteTable();

    public abstract String createUserTable();

    public String createUser() {
        return "INSERT INTO `" + OresomeAchievements.getInstance().storagePrefix + "_users` (`name`) VALUES (?)";
    }

    public String createExactUser() {
        return "INSERT INTO `" + OresomeAchievements.getInstance().storagePrefix + "_users` (`id`,`name`) VALUES (?,?)";
    }

    public String createCompleteAchievement() {
        return "INSERT INTO `" + OresomeAchievements.getInstance().storagePrefix + "_complete` (`name`, `achievement`) VALUES" +
                " (?, ?)";
    }

    public abstract String getColumns(String table);

    public String getUserId() {
        return "SELECT `name` FROM `" + OresomeAchievements.getInstance().storagePrefix + "_users` WHERE `name` = ?";
    }

    public String getUserName(String userId) {
        return "SELECT `name` FROM `" + OresomeAchievements.getInstance().storagePrefix + "_users` WHERE `name` = '" + userId + "'";
    }

    public String getAchievement(String userId) {
        return "SELECT `achievement` FROM `" + OresomeAchievements.getInstance().storagePrefix + "_complete` WHERE `name` = '" + userId + "'";
    }
}