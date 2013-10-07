package com.oresomecraft.Achievements;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigAccess {

    /**
     * Sets details for the map after initiation
     *
     * @param name Name of map
     */
    public static YamlConfiguration loadUserConfig(String name) {
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(new File("plugins/OresomeAchievements/users/", name + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            //Catch an I/O exception whilst loading the user config.
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            //What!? This configuration isn't valid!
        }
        return config;
    }

    public static void saveUserConfig(YamlConfiguration config, String name) {
        try {
            config.save(new File("plugins/OresomeAchievements/users/", name + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            //Catch an I/O exception whilst saving the user config.
        }
    }

    public static boolean userConfigExists(String name){
        return new File("plugins/OresomeAchievements/users/", name + ".yml").isFile();
    }



}
