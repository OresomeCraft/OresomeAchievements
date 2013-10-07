package com.oresomecraft.Achievements;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAccess {

    /**
     * Create the SQL tables if they don't already exist.
     *
     *
     */
    public static void queryCreateTables(){
        if (!OresomeAchievements.getInstance().mysql.isTable(OresomeAchievements.getInstance().storagePrefix + "_users")) {
            try {
                OresomeAchievements.getInstance().mysql.query("CREATE TABLE `" + OresomeAchievements.getInstance().storagePrefix + "_users` (" +
                        "`id` INT(10) UNSIGNED NULL AUTO_INCREMENT," +
                        "`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'," +
                        "PRIMARY KEY (`id`))");
            } catch (SQLException e) {
                e.printStackTrace();  //Meh, this isn't retard proof.
            }
        }


        if (!OresomeAchievements.getInstance().mysql.isTable(OresomeAchievements.getInstance().storagePrefix + "_complete")) {
            try {
                OresomeAchievements.getInstance().mysql.query("CREATE TABLE `" + OresomeAchievements.getInstance().storagePrefix + "_complete` (" +
                        "`id` INT(10) UNSIGNED NULL AUTO_INCREMENT," +
                        "`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'," +
                        "`achievement` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci'," +
                        "PRIMARY KEY (`id`))");
            } catch (SQLException e) {
                e.printStackTrace();  //Meh, this isn't retard proof.
            }
        }
    }

    /**
     * Insert a person into the SQL database if they don't exist.
     *
     */
    public static void queryAddUser(String name){
        ResultSet rs = null;
        try {
            rs = OresomeAchievements.getInstance().mysql.query("SELECT name FROM achievements_users");
        } catch (SQLException e) {
            e.printStackTrace();  //Meh, this isn't retard proof.
        }
        try {
            boolean noadd = false;
            while(rs.next()){
                if(rs.getString("name").equals(name)) noadd = true;
            }
            if(noadd == false){
                OresomeAchievements.getInstance().mysql.query("INSERT INTO achievements_users (`name`) " +
                        " VALUES ('"+name+"')");
            }
        } catch (SQLException e) {
            System.out.println("SQL error occurred whilst trying to insert " + name + " into users DB!");
            e.getMessage();
        }

    }
}
