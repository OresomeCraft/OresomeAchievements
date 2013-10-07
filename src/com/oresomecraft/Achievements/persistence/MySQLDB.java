package com.oresomecraft.Achievements.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oresomecraft.Achievements.OresomeAchievements;
import com.oresomecraft.Achievements.persistence.database.MySQL;
import com.oresomecraft.Achievements.persistence.query.Query;

public class MySQLDB extends SQLDB {
    private MySQL db;
    private ArrayList<String> columns = new ArrayList<String>();

    public Query queryGen;

    public boolean connect(){
        OresomeAchievements.logger.info("Connecting to MySQL.");
        db = new MySQL(OresomeAchievements.logger, OresomeAchievements.getInstance().storageDatabase, OresomeAchievements.getInstance().storageUsername,
                OresomeAchievements.getInstance().storagePassword, OresomeAchievements.getInstance().storageHostname, OresomeAchievements.getInstance().storagePort, OresomeAchievements.getInstance().storagePrefix);
        queryGen = DatabaseManager.getQueryGen();
        try{
            db.open();
            if(!db.checkConnection()) return false;
        }catch(Exception e){
            OresomeAchievements.logger.warning("Failed to connect to the MySQL database.");
            return false;
        }

        try{
            checkTables();
        }catch(Exception e){
            OresomeAchievements.logger.warning("Could not access MySQL tables.");
            return false;
        }
        OresomeAchievements.logger.info("Successfully connected and checked tables, will use MySQL.");
        return true;
    }

    public ResultSet query(String query){
        try {
            return db.query(query);
        } catch (SQLException e) {
            return null;
        }
    }

    private boolean checkTables() throws Exception{
        if(!this.db.isTable(OresomeAchievements.getInstance().storagePrefix + "_completed")){
            if(!db.createTable(queryGen.createUserTable())) return false;
            OresomeAchievements.getInstance().logger.info("Created " + OresomeAchievements.getInstance().storagePrefix + "_completed table.");
        }
        if(!this.db.isTable(OresomeAchievements.getInstance().storagePrefix + "_users")){
            if(!db.createTable(queryGen.createUserTable())) return false;
            OresomeAchievements.getInstance().logger.info("Created " + OresomeAchievements.getInstance().storagePrefix + "_users table.");
        }
        return true;
    }

    public void disconnect(){
        db.close();
    }

    @Override
    public Connection connection() {
        return db.getConnection();
    }

    @Override
    public boolean resetDB() {
        try {
            db.query("TRUNCATE TABLE " + OresomeAchievements.getInstance().storagePrefix + "_complete");
            db.query("TRUNCATE TABLE " + OresomeAchievements.getInstance().storagePrefix + "_users");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean checkTable(String table){
        return db.isTable(table);
    }

    @Override
    public void refresh(){
        this.query("SELECT 1");
    }
}
