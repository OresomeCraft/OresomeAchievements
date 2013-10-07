package com.oresomecraft.Achievements.persistence;

import java.sql.Connection;
import java.sql.ResultSet;

import org.bukkit.Location;

public interface Database {

    public boolean connect();

    public void disconnect();

    public boolean isLoaded();

    public void setLoaded();

    public boolean resetDB();

    public boolean checkTable(String table);

    public Connection connection();

    public void refresh();

}