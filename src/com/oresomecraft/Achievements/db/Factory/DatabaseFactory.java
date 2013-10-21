package com.oresomecraft.Achievements.db.Factory;

import com.oresomecraft.Achievements.db.Database;
import com.oresomecraft.Achievements.db.Delegates.FilenameDatabase;
import com.oresomecraft.Achievements.db.Delegates.FilenameDatabaseImpl;
import com.oresomecraft.Achievements.db.Delegates.HostnameDatabase;
import com.oresomecraft.Achievements.db.Delegates.HostnameDatabaseImpl;
import com.oresomecraft.Achievements.db.Factory.DatabaseConfig.Parameter;
import com.oresomecraft.Achievements.db.MySQL;

/**
 * Factory for Database objects.<br>
 * Date Created: 2012-03-11 15:07.
 *
 * @author Balor (aka Antoine Aflalo)
 */
public class DatabaseFactory {
    public static Database createDatabase(DatabaseConfig config) throws InvalidConfigurationException {
        if (!config.isValid())
            throw new InvalidConfigurationException(
                    "The configuration is invalid, you don't have enought parameters for that DB : "
                            + config.getType());
        switch (config.getType()) {
            case MySQL:
                return new MySQL(config.getLog(), config.getParameter(Parameter.PREFIX),
                        config.getParameter(Parameter.HOSTNAME),
                        Integer.parseInt(config.getParameter(Parameter.PORTNMBR)),
                        config.getParameter(Parameter.DATABASE),
                        config.getParameter(Parameter.USERNAME),
                        config.getParameter(Parameter.PASSWORD));
            default:
                return null;
        }
    }

    public static HostnameDatabase hostname() {
        return new HostnameDatabaseImpl();
    }

    public static FilenameDatabase filename() {
        return new FilenameDatabaseImpl();
    }
}
