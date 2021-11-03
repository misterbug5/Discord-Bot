package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.github.misterbug5.discordbot.entities.Server;
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
        return "manage your current channel\nCommands: setadminchannel, setnotifychannel, setmusicchannel, setlogchannel, settextvisibility, setvoicevisibility";
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
        if (args.get(0).equalsIgnoreCase("setadminchannel")) {
            setAdminChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all commands here are executed as admin\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("setnotifychannel")) {
            setNotifyChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all notifications are shown here\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("setmusicchannel")) {
            VoiceChannel channel = context.getGuildEvent().getMember().getVoiceState().getChannel();
            if (channel == null) {
                context.getChannel().sendMessage("You need to be in a voice channel").queue();
                return;
            }
            setMusicChannel(context.getGuild().getId(), channel.getId());
            context.getChannel().sendMessage("Successfully set\nNow this will be the music channel\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("setlogchannel")) {
            setLogChannel(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow all logs are shown here\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("settextvisibility")) {
            setTextChannelVisibility(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow only roles mentioned can see this channel\nSee all changes here").queue();
            return;
        }
        if (args.get(0).equalsIgnoreCase("setvoicevisibility")) {
            setVoiceChannelVisibility(context.getGuild().getId(), context.getChannel().getId());
            context.getChannel().sendMessage("Successfully set\nNow only roles mentioned can see this channel\nSee all changes here").queue();
            return;
        }
    }

    private void setVoiceChannelVisibility(String id, String channel) {
    }

    private void setTextChannelVisibility(String id, String channel) {
    }

    private void setLogChannel(String id, String channel) {
    }

    private void setMusicChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("musicChannel", channel), Server.class);
    }

    private void setNotifyChannel(String id, String channel) {
    }

    private void setAdminChannel(String id, String channel) {
        database.findAndModify(Query.query(Criteria.where("_id").is(id)), new Update().set("adminChannel", channel), Server.class);
    }
    
}
