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
public class GlitchingI extends OAchievement implements IOAchievement, Listener {

    public GlitchingI() {
        super.initiate(this, name, type, criteria, reward);
    }

    //Objective details
    String name = "Glitching I";
    OAType type = OAType.OBJECTIVE;
    String criteria = "Spectators can't die!";
    int reward = 5;

    public void readyAchievement() {
        //Don't need anything here yet;
    }

    //Make your own code to set off the achievement.
    @EventHandler
    public void checkDeath(PlayerDeathEvent event) {
        if (event.getEntity() != null) {
            try {
                BattlePlayer p = BattlePlayer.getBattlePlayer(event.getEntity());
                if (p.isSpectator()) {
                    callAchievementGet(name, type, criteria, event.getEntity(), 0, reward);
                }
            } catch (NoClassDefFoundError e) {
                //Ignore this achievement if this is being locally tested.
            }
        }
    }
}
