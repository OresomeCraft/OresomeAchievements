package com.oresomecraft.Achievements;

import com.oresomecraft.Achievements.event.AchievementFulfilEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AchievementListener implements Listener {

    OresomeAchievements plugin;

    public AchievementListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void fulfilAchievement(AchievementFulfilEvent event) {
        YamlConfiguration config = new YamlConfiguration();
        config = ConfigAccess.loadUserConfig(event.getPlayer().getName());
        List<String> previousAchievements = config.getStringList(event.getPlayer().getName() + ".completed");
        if (previousAchievements.contains(event.getAchievementName())) return;
        event.getPlayer().sendMessage(ChatColor.YELLOW + "ACHIEVEMENT GET!");
        Firework firework = (Firework)event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.setPower(5);
        FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.YELLOW).trail(true).build();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);
        event.getPlayer().sendMessage(ChatColor.AQUA + event.getAchievementName());
        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + event.getCriteria());
        if (event.getType() == OAType.INCREMENTAL) {
            event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Amount: " + ChatColor.AQUA + event.getIncrement());
        }
        Access.awardPoints(event.getReward());
        event.getPlayer().sendMessage(ChatColor.GREEN + "You were awarded " + event.getReward() + " points!");
        previousAchievements.add(event.getAchievementName());
        config.set(event.getPlayer().getName() + ".completed", previousAchievements);
        try {
            config.save(new File("plugins/OresomeAchievements/users/", event.getPlayer().getName() + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();  //Meh, this isn't retard proof.
        }
    }
}
