package com.oresomecraft.Achievements;

import com.oresomecraft.Achievements.db.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLAccess {

    public static OresomeAchievements plugin = OresomeAchievements.getInstance();

    /**
     *  Created SQL Tables if they don't already exist
     */
    public static synchronized void createTables() {
        if (OresomeAchievements.getInstance().offlineMode) return;

        MySQL mysql = new MySQL(plugin.logger,
                "[AchievementsDB] ",
                plugin.storageHostname,
                plugin.storagePort,
                plugin.storageDatabase,
                plugin.storageUsername,
                plugin.storagePassword);
        mysql.open();

        if (!mysql.isTable("complete")) {
            try {
                mysql.query("CREATE TABLE `complete` (" +
                        "`id` INT(10) UNSIGNED NULL AUTO_INCREMENT," +
                        "`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'," +
                        "`achievement` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'," +
                        "PRIMARY KEY (`id`))");
            } catch (SQLException e) {
                e.printStackTrace();  //Meh, this isn't retard proof.
            }
        }

        mysql.close();
    }

    /**
     * Check if they havent achieved said achievement already, then add the achievement.
     */

    public static synchronized void logComplete(String name, String achievement) {
        if (OresomeAchievements.getInstance().offlineMode) return;

        synchronized (OresomeAchievements.achievementInput) {
            OresomeAchievements.achievementInput.add("INSERT INTO complete (name, achievement) VALUES ('" + name + "', '" + achievement + "')");
        }
    }

    /**
     * Get a players list of completed achievements and cache them.
     */

    public static synchronized ArrayList<String> getCompleted(String name) throws SQLException {
        ArrayList<String> cache = new ArrayList<String>();
        if (OresomeAchievements.getInstance().offlineMode) return cache;

        MySQL mysql = new MySQL(plugin.logger,
                "[AchievementsDB] ",
                plugin.storageHostname,
                plugin.storagePort,
                plugin.storageDatabase,
                plugin.storageUsername,
                plugin.storagePassword);

        mysql.open();

        ResultSet ach = mysql.query("SELECT * FROM complete WHERE name='" + name + "'");
        while (ach.next()) {
            cache.add(ach.getString("achievement"));
        }

        mysql.close();

        return cache;
    }
}
