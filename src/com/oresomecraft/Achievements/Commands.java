package com.oresomecraft.Achievements;

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
            min = 0,
            max = 0)
    @CommandPermissions({"oresomeachievements.list"})
    public void goals(CommandContext args, CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You aren't a player!");
            return; //Don't allow any more interaction
        }
        String complete = ChatColor.GREEN + "Completed!";
        String incomplete = ChatColor.RED + "Incomplete!";
        for (String string : plugin.achs) {
            List<String> list = ConfigAccess.loadUserConfig(sender.getName()).getStringList(sender.getName() + ".completed");
            if (list.contains(string)) {
                sender.sendMessage(ChatColor.AQUA + "- " + string + ChatColor.DARK_AQUA + " (" + complete + ChatColor.DARK_AQUA + ")");
            } else {
                sender.sendMessage(ChatColor.AQUA + "- " + string + ChatColor.DARK_AQUA + " (" + incomplete + ChatColor.DARK_AQUA + ")");
            }
        }
    }

    @Command(aliases = {"achievementinfo", "goalinfo", "milestoneinfo"},
            desc = "View an achievement's info",
            usage = "<achievement>",
            min = 1)
    @CommandPermissions({"oresomeachievements.goalinfo"})
    public void goalInfo(CommandContext args, CommandSender sender) {
        String arg = args.getJoinedStrings(0);
        if (!(plugin.achs.contains(arg))) {
            sender.sendMessage(ChatColor.RED + "That achievement doesn't exist. Remember, achievements are CaSeSeNsItIvE!");
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
            desc = "Oresome",
            usage = "<reset/info/reload>",
            min = 1,
            max = 2)
    public void oac(CommandContext args, CommandSender sender) {
        String arg = args.getString(0);
        if (arg.equalsIgnoreCase("info")) {
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements version " + plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.AQUA + "By R3creat3 and OresomeCraft");
        } else if (arg.equalsIgnoreCase("reload") && sender.hasPermission("oresomeachievements.reload")) {
            //Nothing happens, just for perfectionism.
            sender.sendMessage(ChatColor.AQUA + "OresomeAchievements reloaded");
        } else if (arg.equalsIgnoreCase("reset") && args.argsLength() == 2 && sender.hasPermission("oresomeachievements.reset")){
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
            sender.sendMessage(ChatColor.RED + "Correct usage: /oac <reset/info/reload>");
        }
    }
}