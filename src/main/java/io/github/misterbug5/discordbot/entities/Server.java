package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;
import java.util.EnumSet;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

@Document(collection = "servers")
public class Server {
    @MongoId private String id;
    private ArrayList<Perm> serverPerms;
    private ArrayList<Command> commands;
    private String adminChannel;
    private String musicChannel;
    private String prefix;

    public Server(Guild guild) {
        this.id = guild.getId();
        this.serverPerms = GenericAttributes.getServerPerms(guild);
        this.commands = GenericAttributes.getServerCommands();
        Category cat = guild.createCategory("Admin").addPermissionOverride(guild.getOwner(), EnumSet.of(Permission.VIEW_CHANNEL), null).addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)).complete();
        TextChannel adminChannel = cat.createTextChannel("Notifications").complete();
        adminChannel.sendMessage("Joined Successfully to " + guild.getName() + "\nType '>Help' to start").queue();
        this.adminChannel = adminChannel.getId();
        this.musicChannel = null;
        this.prefix = GenericAttributes.getPrefix();
    }

    public Server() {
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public ArrayList<Perm> getServerPerms() {
        return serverPerms;
    }
    
    public void setServerPerms(ArrayList<Perm> serverPerms) {
        this.serverPerms = serverPerms;
    }
    
    public ArrayList<Command> getCommands() {
        return commands;
    }
    
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
    
    public String getAdminChannel() {
        return adminChannel;
    }
    
    public void setAdminChannel(String adminChannel) {
        this.adminChannel = adminChannel;
    }
    
    public String getMusicChannel() {
        return musicChannel;
    }
    
    public void setMusicChannel(String musicChannel) {
        this.musicChannel = musicChannel;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
