package com.oresomecraft.Achievements.persistence;

import java.sql.Connection;

import com.oresomecraft.Achievements.OresomeAchievements;
import com.oresomecraft.Achievements.persistence.query.*;

public class DatabaseManager {
    private static Database database;
    private static Query queryGen;

    public static boolean load(){
        String type = OresomeAchievements.getInstance().storageType;
        if(type.equalsIgnoreCase("mysql")){
            if(loadMySQL()){
                database.setLoaded();
                return true;
            }
        }
        return false;
    }

    private static boolean loadMySQL(){
        database = new MySQLDB();
        queryGen = new MySQLQuery();
        return database.connect();
    }

    public static Database getDatabase(){
        return database;
    }

    public static Connection getConnection(){
        return database.connection();
    }

    public static Query getQueryGen(){
        return queryGen;
    }
}
