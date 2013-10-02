package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

public class Torture extends OAchievement implements IOAchievement, Listener {

    public Torture() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Torture";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Die whilst on fire and poisoned!";
    int reward = 10;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getFireTicks() > 0 ) {
            Collection<PotionEffect> effects = event.getEntity().getActivePotionEffects();
            boolean check = false;
            for(PotionEffect effect : effects){
                if(effect.getType().equals(PotionEffectType.POISON)) check = true;
            }
            callAchievementGet(name, type, criteria, event.getEntity(), 0, reward, ConfigAccess.loadUserConfig(event.getEntity().getName()));
        }
    }
}
