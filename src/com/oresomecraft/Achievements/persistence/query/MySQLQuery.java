package com.oresomecraft.Achievements.persistence.query;

import com.oresomecraft.Achievements.OresomeAchievements;

public class MySQLQuery extends Query{

    @Override
    public String createCompleteTable() {
        return "CREATE TABLE `" + OresomeAchievements.getInstance().storagePrefix + "_complete` (" +
                "`id` INT(10) UNSIGNED NULL AUTO_INCREMENT," +
                "`name` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'," +
                "`achievement` VARCHAR(255) UNSIGNED NULL DEFAULT 'Achievement'," +
                "PRIMARY KEY (`id`))";
    }

    @Override
    public String createTemporaryCompleteTable() {
        return null;
    }

    @Override
    public String createUserTable() {
        return "CREATE TABLE `" + OresomeAchievements.getInstance().storagePrefix + "_users` (" +
                "`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci'," +
                "PRIMARY KEY (`id`))";
    }

    @Override
    public String getColumns(String table) {
        return "show columns from `" + table + "`";
    }
}