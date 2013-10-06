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
import java.util.ArrayList;
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
    @CommandPermissions({"oresomeachievements.list"})
    public void goals(CommandContext args, CommandSender sender) {
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
        boolean stopcheck = false;
        while (i < maxPage && stopcheck == false) {
            try {
                String done = ChatColor.GREEN + "" +ChatColor.BOLD + " +";
                String notdone = ChatColor.RED + "" + ChatColor.BOLD + " -";
                List<String> list = ConfigAccess.loadUserConfig(sender.getName()).getStringList(sender.getName()+".completed");
                if(list.contains(plugin.achs.get(i))){
                sender.sendMessage(ChatColor.DARK_AQUA + "- " + ChatColor.AQUA + plugin.achs.get(i) + done);
                }else{
                    sender.sendMessage(ChatColor.DARK_AQUA + "- " + ChatColor.AQUA + plugin.achs.get(i) + notdone);
                }
                i++;
            } catch (IndexOutOfBoundsException e) {
                sender.sendMessage(ChatColor.RED + "No further achievements found.");
                i++;
                stopcheck = true;
            }
        }
        sender.sendMessage(ChatColor.GOLD + "To see next page, type '/achievements " + (page + 1) + "'");
    }

    @Command(aliases = {"complete", "fulfilled", "completedachievements"},
            desc = "View a players' complete achievements",
            usage = "<player> <page>",
            min = 0,
            max = 2)
    @CommandPermissions({"oresomeachievements.list"})
    public void complete(CommandContext args, CommandSender sender) {
        int page = 1;
        if(args.argsLength() == 0){
            Bukkit.dispatchCommand(sender, "complete " + sender.getName() + " 1");
            return;
        }
        String player = args.getString(0);
        String playerActual = args.getString(0);
        if(args.argsLength() == 1){
            if(ConfigAccess.userConfigExists(player)){
                Bukkit.dispatchCommand(sender, "complete " + player + " " + page);
                return;
            }
            try{
            page = Integer.parseInt(player);
            Bukkit.dispatchCommand(sender, "complete " + sender.getName() + " " + page);
            }catch(NumberFormatException e){
                Bukkit.dispatchCommand(sender, "complete " + sender.getName() + " " + page);
                sender.sendMessage(ChatColor.RED + "That is not a number!");
                return;
            }
            return;
        }
        if(ConfigAccess.userConfigExists(playerActual) == false && args.argsLength() == 2){
            sender.sendMessage(ChatColor.RED + "That user doesn't exist!");
            return;
        }
        if (args.argsLength() == 2) {
            try {
                page = Integer.parseInt(args.getString(1));
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "That is not a number!");
                return;
            }
        }
        List<String> achs = ConfigAccess.loadUserConfig(player).getStringList(player+".completed");
        int maxPage = page * 10;
        int i = maxPage - 10;
        sender.sendMessage(ChatColor.GOLD + player + "'s achievement list (Page " + page + ")");
        //10 per page, so if it's page 2 it will check the array-list from 10-20.
        boolean stopcheck = false;
        while (i < maxPage && stopcheck == false) {
            try {
                sender.sendMessage(ChatColor.DARK_AQUA + "- " + ChatColor.AQUA + achs.get(i));
                i++;
            } catch (IndexOutOfBoundsException e) {
                sender.sendMessage(ChatColor.RED + "No further achievements found.");
                i++;
                stopcheck = true;
            }
        }
        sender.sendMessage(ChatColor.GOLD + "To see next page, type '/achievements " + player + " " + (page + 1) + "'");
    }

    @Command(aliases = {"achievementinfo", "goalinfo", "milestoneinfo", "achinfo"},
            desc = "View an achievement's info",
            usage = "<achievement>",
            min = 1)
    @CommandPermissions({"oresomeachievements.goalinfo"})
    public void goalInfo(CommandContext args, CommandSender sender) {
        String arg = args.getJoinedStrings(0);
        if (!(plugin.achs.contains(arg))) {
            sender.sendMessage(ChatColor.RED + "That achievement doesn't exist!");
            return;
        }
        for (Map.Entry<String, String> entry : plugin.criteria.entrySet()) {
            if (entry.getKey().equals(arg)) {
                List<String> list = ConfigAccess.loadUserConfig(sender.getName()).getStringList(sender.getName() + ".completed");
                String yesorno = ChatColor.RED + "Incomplete";
                if (list.contains(entry.getKey())) {
                    yesorno = ChatColor.GREEN + "Complete";
                }
                sender.sendMessage(ChatColor.DARK_AQUA + "Information about " + ChatColor.AQUA + entry.getKey());
                sender.sendMessage(ChatColor.DARK_AQUA + "Criteria: " + ChatColor.AQUA + entry.getValue());
                sender.sendMessage(ChatColor.DARK_AQUA + "Completed: " + yesorno);
            }
        }
    }

    @Command(aliases = {"oresomeachievements", "oac", "oresomegoals"},
            desc = "Various OresomeAchievement commands",
            usage = "<reset/info/reload>",
            min = 1)
    public void oac(CommandContext args, CommandSender sender) {
        String arg = args.getString(0);
        if (arg.equalsIgnoreCase("info") && args.argsLength() == 1) {
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements version " + plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.AQUA + "By R3creat3 and OresomeCraft");
        } else if (arg.equalsIgnoreCase("reload") && sender.hasPermission("oresomeachievements.reload") && args.argsLength() == 1) {
            //Nothing happens, just for perfectionism.
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements reloaded");
        } else if (arg.equalsIgnoreCase("reset") && args.argsLength() == 2 && sender.hasPermission("oresomeachievements.reset")) {
            if (new File("plugins/OresomeAchievements/users", args.getString(1) + ".yml").isFile()) {
                File file = new File("plugins/OresomeAchievements/users", args.getString(1) + ".yml");
                file.delete();
                sender.sendMessage(ChatColor.AQUA + "User's configuration was deleted!");
                if (Bukkit.getPlayer(args.getString(1)) instanceof Player) {
                    Bukkit.getPlayer(args.getString(1)).kickPlayer(ChatColor.RED + "Your configuration was reset, relogin!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "That user has not been seen before!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "/oresomeachievements <reset/info/reload>");
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
        if (plugin.achs.contains(arg)) {
            for (Map.Entry<String, String> entry : plugin.criteria.entrySet()) {
                if (entry.getKey().equals(arg)) {
                    Bukkit.getPluginManager().callEvent(new AchievementFulfilEvent((Player) sender, arg, 0, entry.getValue(), 0, OAType.FORCED));
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "That achievement doesn't exist!");
        }
    }
}