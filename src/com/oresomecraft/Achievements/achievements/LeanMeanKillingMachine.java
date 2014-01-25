package com.oresomecraft.Achievements.achievements;

import com.oresomecraft.Achievements.Achievement;
import com.oresomecraft.Achievements.IOAchievement;
import com.oresomecraft.Achievements.OAType;
import com.oresomecraft.Achievements.OAchievement;
import com.oresomecraft.OresomeBattles.api.BattlePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@Achievement
public class LeanMeanKillingMachine extends OAchievement implements IOAchievement, Listener {

    public LeanMeanKillingMachine() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Lean Mean Killing Machine";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Beat the current highest battles streak!";
    int reward = 20;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            try {
                BattlePlayer p = BattlePlayer.getBattlePlayer(event.getEntity().getKiller().getName());
                if (p.getKillStreak() >= 83) {
                    callAchievementGet(name, type, criteria, event.getEntity().getKiller(), 0, reward);
                }
            } catch (NoClassDefFoundError e) {
                //Ignore this achievement if this is being locally tested.
            }
        }
    }

}