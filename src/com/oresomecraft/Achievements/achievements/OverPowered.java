package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.ConfigAccess;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import java.util.Map;

public class OverPowered extends OAchievement implements IOAchievement, Listener {

    public OverPowered() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Over Powered";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Can you get a sharpness II fire aspect I sword?";
    int reward = 15;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkEnchant(EnchantItemEvent event) {
        Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
        int check = 0;
        for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
            if (entry.getKey().equals(Enchantment.DAMAGE_ALL)) {
                if (entry.getValue() == 2) {
                    check++;
                }
            }
            if (entry.getKey().equals(Enchantment.FIRE_ASPECT)) {
                if (entry.getValue() == 1) {
                    check++;
                }
            }
        }
        //If both enchantments were checked out true, then we can call the achievement :D
        if (check == 2) {
            Player p = (Player) event.getInventory().getHolder();
            String pname = p.getName();
            callAchievementGet(name, type, criteria, (Player) event.getInventory().getHolder(), 0, reward, ConfigAccess.loadUserConfig(pname));
        }
    }
}
