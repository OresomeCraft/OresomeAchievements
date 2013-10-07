package com.oresomecraft.Achievements.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLDB implements Database{

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded() {
        loaded = true;
    }

    public abstract ResultSet query(String query);

    private boolean iterateData(ResultSet data) {
        try {
                return data.next();
            } catch (SQLException e) {
                return false;
            }
    }

    private String getString(ResultSet data, String label) {
        try {
            return data.getString(label);
            } catch (SQLException e) {
                return null;
            }
    }

    private int getInt(ResultSet data, String label) {
        try {
            return data.getInt(label);
            } catch (SQLException e) {
                return 0;
            }
    }
}
