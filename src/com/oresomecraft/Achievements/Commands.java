package com.oresomecraft.Achievements;

import com.oresomecraft.Achievements.event.AchievementFulfilEvent;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Commands {
    OresomeAchievements plugin;

    public Commands(OresomeAchievements pl) {
        plugin = pl;
    }

    @Command(aliases = {"achievements", "goals", "milestones"},
            desc = "View the achievements",
            usage = "<page>",
            min = 0,
            max = 1)
    public void goals(CommandContext args, CommandSender sender) throws SQLException {
        int page = 1;
        if (args.argsLength() == 1) {
            try {
                page = Integer.parseInt(args.getString(0));
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "That is not a number!");
                return;
            }
        }
        int maxPage = page * 10;
        int i = maxPage - 10;
        sender.sendMessage(ChatColor.GOLD + "Achievements List (Page " + page + ")");
        //10 per page, so if it's page 2 it will check the array-list from 10-20.
        boolean stopCheck = false;
        while (i < maxPage && !stopCheck) {
            try {
                sender.sendMessage(ChatColor.DARK_AQUA + "- " + ChatColor.AQUA + plugin.achievements.get(i));
                i++;
            } catch (IndexOutOfBoundsException e) {
                sender.sendMessage(ChatColor.RED + "No further achievements found.");
                i++;
                stopCheck = true;
            }
        }
        sender.sendMessage(ChatColor.GOLD + "To see next page, type '/achievements " + (page + 1) + "'");
    }

    @Command(aliases = {"achievementinfo", "goalinfo", "milestoneinfo", "achinfo"},
            desc = "View an achievement's info",
            usage = "<achievement>",
            min = 1)
    public void goalInfo(CommandContext args, CommandSender sender) {
        String arg = args.getJoinedStrings(0);
        
        if (matchAchievement(arg).equals("None")) {
            sender.sendMessage(ChatColor.RED + "That achievement doesn't exist!");
            return;
        }
        String ach = matchAchievement(arg);
        AchievementPlayer ap = AchievementPlayer.getAchievementPlayer(sender.getName());
        List<String> list = ap.getCompletedAchievements();
        String yesorno = ChatColor.RED + "Incomplete";
        if (list.contains(ach)) {
            yesorno = ChatColor.GREEN + "Complete";
        }
        sender.sendMessage(ChatColor.DARK_AQUA + "Information about " + ChatColor.AQUA + ach);
        sender.sendMessage(ChatColor.DARK_AQUA + "Details: " + ChatColor.AQUA + OresomeAchievements.getInstance().criteria.get(ach));
        sender.sendMessage(ChatColor.DARK_AQUA + "Completed: " + yesorno);

    }

    @Command(aliases = {"oresomeachievements", "oac", "oresomegoals"},
            desc = "Various OresomeAchievement commands",
            usage = "<info/reload>",
            min = 1)
    @CommandPermissions({"oresomeachievements.staff"})
    public void oac(CommandContext args, CommandSender sender) {
        String arg = args.getString(0);
        if (arg.equalsIgnoreCase("info") && args.argsLength() == 1) {
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements version " + plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.AQUA + "By __R3 and OresomeCraft");
        } else if (arg.equalsIgnoreCase("reload") && sender.hasPermission("oresomeachievements.reload") && args.argsLength() == 1) {
            //Nothing happens, just for perfectionism.
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements reloaded");
        }
    }

    @Command(aliases = {"achforce", "goalforce"},
            desc = "Force an achievement to yourself",
            usage = "<achievement>",
            min = 1)
    @CommandPermissions({"oresomeachievements.force"})
    //This is used primarily for testing new achievements or restoring lost achievements. Don't be an idiot and abuse it.
    public void achForce(CommandContext args, CommandSender sender) {
        String arg = args.getJoinedStrings(0);
        if (matchAchievement(arg).equals("None")) {
            for (Map.Entry<String, String> entry : plugin.criteria.entrySet()) {
                if (entry.getKey().equals(arg)) {
                    Bukkit.getPluginManager().callEvent(new AchievementFulfilEvent((Player) sender, arg, 0, entry.getValue(), 0, OAType.FORCED));
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "That achievement doesn't exist!");
        }
    }

    @Command(aliases = {"oacquery"},
            desc = "Make a query in the OresomeAchievements SQL DB",
            usage = "<query>",
            flags = "e",
            min = 1)
    @CommandPermissions({"oresomeachievements.query"})
    public void query(CommandContext args, CommandSender sender) {
        //Will do later
        sender.sendMessage(ChatColor.RED + "Disabled at the moment.");
    }

    private String matchAchievement(String ach) {
        for (String s : OresomeAchievements.getInstance().achievements) {
            String temp = s;
            if (s.toLowerCase().startsWith(ach.toLowerCase())) {
                return temp;
            }
        }
        return "None";
    }
}