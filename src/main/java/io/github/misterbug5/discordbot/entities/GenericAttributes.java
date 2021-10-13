package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.misterbug5.discordbot.listeners.commands.Actions;
import io.github.misterbug5.discordbot.listeners.commands.Arguments;
import net.dv8tion.jda.api.entities.Guild;

public class GenericAttributes {
    public static ArrayList<Perm> getServerPerms(Guild guild) {
        ArrayList<Perm> perms = new ArrayList<Perm>();
        RollPerm rollPerm = new RollPerm();
        rollPerm.setName("General Role");
        rollPerm.addRoll(guild.getPublicRole().getId());
        perms.add(rollPerm);
        UserPerm adminPerm = new UserPerm();
        adminPerm.setName("Admin Users");
        adminPerm.addUser(guild.getOwnerId());
        perms.add(adminPerm);
        return perms;
    }

    public static ArrayList<Command> getServerCommands() {
        ArrayList<Command> perms = new ArrayList<Command>();
        perms.add(new Command("help", new ArrayList<String>(Arrays.asList("General Role"))){
            {
                addAction(Actions.HELP);
            }
        });
        perms.add(new Command("ping", new ArrayList<String>(Arrays.asList("General Role"))){
            {
                addAction(Actions.SAY, Arguments.PING);
            }
        });
        perms.add(new Command("ban", new ArrayList<String>(Arrays.asList("Admin Users"))){
            {
                addAction(Actions.BAN, Arguments.USER, Arguments.REASON, Arguments.FOR_TIME);
            }
        });
        perms.add(new Command("permanentBan", new ArrayList<String>(Arrays.asList("Admin Users"))){
            {
                addAction(Actions.BAN, Arguments.USER, Arguments.REASON);
            }
        });
        return perms;
    }

    public static String getPrefix() {
        return ">";
    }
}
