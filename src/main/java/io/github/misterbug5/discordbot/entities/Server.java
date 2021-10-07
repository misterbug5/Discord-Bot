package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "servers")
public class Server {
    @MongoId private String id;
    private ArrayList<Perm> serverPerms;
    private ArrayList<Command> commands;
    private String adminChannel;
    private String musicChannel;
    private String prefix;

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
