package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.github.misterbug5.discordbot.entities.Server;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class Channel implements ICommand {
    private MongoTemplate database;

    public Channel(MongoTemplate database) {
        this.database = database;
    }

    @Override
    public String getName() {
        return "channel";
    }

    @Override
    public String getHelp() {
        return "manage your current channel\nCommands: adminchannel, notifychannel, musicchannel, logchannel, textvisibility, voicevisibility";
    }

    @Override
    public String getUsage(String prefix) {
        return String.format("%schannel or %sadmin channel", prefix, prefix);
    }

    @Override
    public void execute(CommandContext context) {
        context.getChannel().sendMessageFormat("You are in %s", context.getChannel().getName()).queue();
    }

    @Override
    public void admin(CommandContext context) {
        ArrayList<String> args = context.getArgs();
        if (args.isEmpty()) {
            context.getChannel().sendMessageFormat("You are in %s", context.getChannel().getName()).queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("adminchannel")) {
            setAdminChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all commands here are executed as admin\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("notifychannel")) {
            setNotifyChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all notifications are shown here\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("musicchannel")) {
            VoiceChannel channel = context.getVoiceChannel();
            if (channel == null) {
                context.getChannel().sendMessage("You need to be in a voice channel").queue();
                return;
            }
            setMusicChannel(context.getGuild().getId(), channel.getId());
            context.getChannel().sendMessage("Successfully set\nNow this will be the music channel\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("logchannel")) {
            setLogChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all logs are shown here\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("textvisibility")) {
            boolean mentioned = false;
            if(!context.getMessage().getMentionedRoles().isEmpty()){
                setChannelVisibilityRole(context.getTextChannel(), context.getMessage().getMentionedRoles());
                mentioned = true;
            }
            if(!context.getMessage().getMentionedMembers().isEmpty()){
                setChannelVisibilityMember(context.getTextChannel(), context.getMessage().getMentionedMembers());
                mentioned = true;
            }
            if (mentioned) {
                context.getChannel().sendMessage("Successfully set\nNow only roles mentioned can see this channel\nSee all changes here").queue();
            } else {
                context.getChannel().sendMessage("You must mention a user or role").queue();
            }
            return;
        }
        if (args.get(0).equalsIgnoreCase("voicevisibility")) {
            VoiceChannel channel = context.getVoiceChannel();
            if (channel == null) {
                context.getChannel().sendMessage("You need to be in a voice channel").queue();
                return;
            }
            boolean mentioned = false;
            if(!context.getMessage().getMentionedRoles().isEmpty()){
                setChannelVisibilityRole(channel, context.getMessage().getMentionedRoles());
                mentioned = true;
            }
            if(!context.getMessage().getMentionedMembers().isEmpty()){
                setChannelVisibilityMember(channel, context.getMessage().getMentionedMembers());
                mentioned = true;
            }
            if (mentioned) {
                context.getChannel().sendMessage("Successfully set\nNow only roles mentioned can see this channel\nSee all changes here").queue();
            } else {
                context.getChannel().sendMessage("You must mention a user or role").queue();
            }
            return;
        }
    }

    private void setChannelVisibilityMember(TextChannel channel, List<Member> mentionedMembers) {
        for (Member member : mentionedMembers) {
            channel.upsertPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL).queue();
        }
    }

    private void setChannelVisibilityRole(TextChannel channel, List<Role> mentionedRoles) {
        for (Role role : mentionedRoles) {
            channel.upsertPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL).queue();
        }
    }

    private void setChannelVisibilityRole(VoiceChannel channel, List<Role> mentionedRoles) {
        for (Role role : mentionedRoles) {
            channel.upsertPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
        }
    }

    private void setChannelVisibilityMember(VoiceChannel channel, List<Member> mentionedMembers) {
        for (Member member : mentionedMembers) {
            channel.upsertPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT).queue();
        }
    }

    private void setLogChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("logChannel", channel), Server.class);
    }

    private void setMusicChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("musicChannel", channel), Server.class);
    }

    private void setNotifyChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("notificationsChannel", channel), Server.class);
    }

    private void setAdminChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("adminChannel", channel), Server.class);
    }
    
}
