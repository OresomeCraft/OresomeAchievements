package com.oresomecraft.Achievements;

import com.oresomecraft.Achievements.event.AchievementCheckpointEvent;
import com.oresomecraft.Achievements.event.AchievementFulfilEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;

public class AchievementListener implements Listener {

    OresomeAchievements plugin;

    public AchievementListener(OresomeAchievements pl) {
        plugin = pl;
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void fulfilAchievement(AchievementFulfilEvent event) {
        try {
            //Have you already achieved this?
            if (SQLAccess.queryAlreadyAchieved(event.getPlayer().getName(), event.getAchievementName())) return;
            awardAchievement(event.getPlayer(), event.getAchievementName(), event.getCriteria(), event.getIncrement(), event.getReward(), event.getType());
            SQLAccess.queryInsertComplete(event.getPlayer().getName(), event.getAchievementName());
        } catch (AchievementException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void checkpointAchievement(AchievementCheckpointEvent event) {
        event.getPlayer().sendMessage(ChatColor.YELLOW + "########" + ChatColor.GOLD + "ORESOMEACHIEVEMENTS" + ChatColor.YELLOW + "########");
        event.getPlayer().sendMessage(ChatColor.GOLD + "                   Keep going!");
        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "You are doing great with the " + ChatColor.AQUA +
                event.getAchievementName() + ChatColor.DARK_AQUA + " achievement!");
        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "Amount: " + ChatColor.AQUA + event.getIncrement());
        event.getPlayer().sendMessage(ChatColor.YELLOW + "###################################");
    }

    private void awardAchievement(Player player, String name, String criteria, int increment, int reward, OAType type) throws AchievementException {
        player.sendMessage(ChatColor.YELLOW + "########" + ChatColor.GOLD + "ORESOMEACHIEVEMENTS" + ChatColor.YELLOW + "########");
        player.sendMessage(ChatColor.YELLOW + "ACHIEVEMENT GET!");
        Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.setPower(1);
        FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.YELLOW).trail(true).with(FireworkEffect.Type.BALL_LARGE).build();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);
        player.sendMessage(ChatColor.AQUA + name + ChatColor.DARK_AQUA + " (" + criteria + ")");
        if (type == OAType.INCREMENTAL) {
            player.sendMessage(ChatColor.DARK_AQUA + "Amount: " + ChatColor.AQUA + increment);
        }
        Access.awardPoints(player, reward);
        player.sendMessage(ChatColor.GREEN + "You were awarded " + reward + " points!");
        player.sendMessage(ChatColor.YELLOW + "###################################");
    }
}
