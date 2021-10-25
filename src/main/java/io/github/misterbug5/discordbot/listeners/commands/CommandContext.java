package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class CommandContext {
    private User user;
    private JDA bot;
    private Message message;
    private MessageChannel channel;
    private ArrayList<ICommand> commands;
    private ArrayList<String> argList;
    private Long time;
    private Guild guild;
    private String prefix;

    public CommandContext(ArrayList<String> argList, ArrayList<ICommand> commands, PrivateMessageReceivedEvent event, String prefix) {
        this.commands = commands;
        this.time = System.currentTimeMillis();
        this.argList = argList;
        this.user = event.getAuthor();
        this.bot = event.getJDA();
        this.message = event.getMessage();
        this.channel = event.getChannel();
        this.guild = null;
        this.prefix = prefix;
    }

    public CommandContext(ArrayList<String> argList, ArrayList<ICommand> commands, GuildMessageReceivedEvent event, String prefix) {
        this.commands = commands;
        this.time = System.currentTimeMillis();
        this.argList = argList;
        this.user = event.getAuthor();
        this.bot = event.getJDA();
        this.message = event.getMessage();
        this.channel = event.getChannel();
        this.guild = event.getGuild();
        this.prefix = prefix;
    }

    public User getUser() {
        return user;
    }

    public JDA getBot() {
        return bot;
    }

    public Message getMessage() {
        return message;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public ArrayList<String> getArgs(){
        return argList;
    }

    public Long getTime(){
        return time;
    }

    public ArrayList<ICommand> getCommands() {
        return commands;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getPrefix() {
        return prefix;
    }

}
