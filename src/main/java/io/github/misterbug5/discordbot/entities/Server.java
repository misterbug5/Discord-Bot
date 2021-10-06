package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "servers")
public class Server {
    @MongoId private Long id;
    private ArrayList<Perm> serverPerms;
    private ArrayList<Command> commands;
    private Long adminChannel;
    private Long musicChannel;
    private String prefix;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
    
    public Long getAdminChannel() {
        return adminChannel;
    }
    
    public void setAdminChannel(Long adminChannel) {
        this.adminChannel = adminChannel;
    }
    
    public Long getMusicChannel() {
        return musicChannel;
    }
    
    public void setMusicChannel(Long musicChannel) {
        this.musicChannel = musicChannel;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
