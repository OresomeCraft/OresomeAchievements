package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BigStreak extends OAchievement implements IOAchievement, Listener {

    public BigStreak() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Big Streak";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Get a kill streak of over 50 to unlock!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            try {
                BattlePlayer p = BattlePlayer.getBattlePlayer(event.getEntity().getKiller());
                if (p.getKillStreak() >= 50) {
                    callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
                }
            } catch (NoClassDefFoundError e) {
                //Ignore this achievement if this is being locally tested.
            }
        }
    }


}
