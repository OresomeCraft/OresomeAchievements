package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BlastProof extends OAchievement implements IOAchievement, Listener {

    public BlastProof() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Blast Proof";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Survive an explosion, but take 7 hearts of damage!";
    int reward = 25;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkEntity(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.getGameMode() == GameMode.CREATIVE) return;
            try {
                EntityDamageEvent.DamageCause cause = p.getLastDamageCause().getCause();
                if (cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                    if (p.getLastDamage() > 14 && !p.isDead()) {
                        callAchievementGet(name, type, criteria, p, 0, reward);
                    }
                }
            } catch (NullPointerException e) {
                //Sometimes this doesn't check, and I don't want it spamming the console with a null pointer.
            }
        }
    }
}
